package com.imagepicker.imagepicker.adapters;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.imagepicker.imagepicker.R;

import java.io.IOException;
import java.io.InputStream;

public class ImageSelectorAdapter extends RecyclerView.Adapter<ImageSelectorAdapter.ViewHolder> {

    private final String[] mImgList;
    private final Context mContext;
    private final AssetManager mAssetManager;

    public ImageSelectorAdapter(Context c, String[] imgList) {
        mImgList = imgList;
        mContext = c;
        mAssetManager = mContext.getAssets();
    }

    @NonNull
    @Override
    public ImageSelectorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d("ImagePicker", "onCreateViewHolder "+i);
        LinearLayout layout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_checkbox_holder, viewGroup, false);

        ViewHolder imageHolder = new ViewHolder(layout);
        return imageHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d("ImagePicker", "onBindViewHolder "+mImgList[i]);

        try {
            Glide.with(mContext).load(BitmapFactory.decodeStream(mAssetManager.open("images/"+mImgList[i])))
            .into(viewHolder.imageView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mImgList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public CheckBox imageCheckBox;

        public ViewHolder(@NonNull LinearLayout layout) {
            super(layout);
            imageView = layout.findViewById(R.id.image);
            imageCheckBox = layout.findViewById(R.id.imgCheckbox);
        }
    }
}
