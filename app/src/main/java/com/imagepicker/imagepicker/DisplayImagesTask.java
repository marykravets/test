package com.imagepicker.imagepicker;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.imagepicker.imagepicker.adapters.ImageDetailAdapter;
import com.imagepicker.imagepicker.adapters.ImageSelectorAdapter;

import java.io.IOException;
import java.lang.ref.WeakReference;

public class DisplayImagesTask extends AsyncTask {
    String[] imgList = new String[0];
    private WeakReference<AppCompatActivity> activity;
    private int mViewId;

    public DisplayImagesTask(AppCompatActivity activity, int viewId) {
        this.activity = new WeakReference<>(activity);
        this.mViewId = viewId;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        if(activity.get() != null) {
            try {
                imgList = activity.get().getAssets().list("images");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object result) {
        if(activity.get() != null) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity.get());
            RecyclerView imgSelectList = activity.get().findViewById(mViewId);

            if (mViewId == R.id.imageSelectorView) {
                imgSelectList.setAdapter(new ImageSelectorAdapter(activity.get(), imgList));
            } else {
                imgSelectList.setAdapter(new ImageDetailAdapter(activity.get(), imgList));
            }

            imgSelectList.setLayoutManager(layoutManager);
        }
    }
}
