package com.imagepicker.imagepicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.imagepicker.imagepicker.adapters.ImageDetailAdapter;

import java.io.IOException;

public class DetailListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list);

        String[] imgList = new String[0];
        try {
            imgList = getAssets().list("images");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageDetailAdapter adapter = new ImageDetailAdapter(this, imgList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        RecyclerView imgSelectList = findViewById(R.id.imageDetailView);
        imgSelectList.setAdapter(adapter);
        imgSelectList.setLayoutManager(layoutManager);
    }
}
