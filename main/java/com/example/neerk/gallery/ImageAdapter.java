package com.example.neerk.gallery;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by neerk on 13/12/17.
 */

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private Integer[] mImageList;

    public ImageAdapter(Context mContext,Integer[] mImageList) {
        this.mContext=mContext;
        this.mImageList = mImageList;
    }

    @Override
    public int getCount() {
        return mImageList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setPadding(16, 16, 16, 16);

        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mImageList[position]);
        return imageView;
    }
}
