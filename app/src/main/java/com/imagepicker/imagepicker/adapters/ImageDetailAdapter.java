package com.imagepicker.imagepicker.adapters;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.imagepicker.imagepicker.R;

import java.io.IOException;

public class ImageDetailAdapter extends RecyclerView.Adapter<ImageDetailAdapter.ViewHolder> {

    private final String[] mImgList;
    private final Context mContext;
    private final AssetManager mAssetManager;

    public ImageDetailAdapter(Context c, String[] imgList) {
        mImgList = imgList;
        mContext = c;
        mAssetManager = mContext.getAssets();
    }

    @NonNull
    @Override
    public ImageDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ImageView image = (ImageView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_holder, viewGroup, false);

        ImageDetailAdapter.ViewHolder imageHolder = new ImageDetailAdapter.ViewHolder(image);
        return imageHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageDetailAdapter.ViewHolder viewHolder, int i) {
        if(mImgList[i].contains("thumbnail")) {
            try {
                viewHolder.itemView.setBackground(new BitmapDrawable(mContext.getResources(),
                        BitmapFactory.decodeStream(mAssetManager.open("images/"+mImgList[i]))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return mImgList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(@NonNull ImageView itemView) {
            super(itemView);
            imageView = itemView;
        }
    }
}
