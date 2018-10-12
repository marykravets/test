package com.imagepicker.imagepicker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.imagepicker.imagepicker.adapters.ImageSelectorAdapter;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] imgList = getImagesList();
        Log.d("ImagePicker", "assets list size ="+ imgList.length);

        initButton();
        setAdapter(imgList);
    }

    private String[] getImagesList() {
        String[] imgList = new String[0];
        try {
            imgList = getAssets().list("images");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgList;
    }

    private void initButton() {
        Button btn = findViewById(R.id.nextBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setAdapter(String[] imgList) {
        ImageSelectorAdapter adapter = new ImageSelectorAdapter(this, imgList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        RecyclerView imgSelectList = findViewById(R.id.imageSelectorView);
        imgSelectList.setAdapter(adapter);
        imgSelectList.setLayoutManager(layoutManager);
    }
}
