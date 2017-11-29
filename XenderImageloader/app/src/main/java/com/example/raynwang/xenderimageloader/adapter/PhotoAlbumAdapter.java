package com.example.raynwang.xenderimageloader.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.raynwang.xenderimageloader.R;
import com.example.raynwang.xenderimageloader.bean.AlbumBean;
import com.example.raynwang.xenderimageloader.bean.Photo;
import com.example.raynwang.xenderimageloader.fragment.subfragment.PhotoAlbumFragment;
import com.truizlop.sectionedrecyclerview.SectionedRecyclerViewAdapter;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2016/8/25.
 */
public class PhotoAlbumAdapter extends RecyclerView.Adapter {
    private List<AlbumBean> albumBeanList;
    Context context;

    public PhotoAlbumAdapter(List<AlbumBean> albumBeanList,Context context) {
        this.albumBeanList = albumBeanList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_photo_album_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewholder = (MyViewHolder) holder;

        Glide.with(context)
                .load(new File(albumBeanList.get(position).topImagePath))
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(myViewholder.mImageView);

        myViewholder.mTextViewTitle.setText(albumBeanList.get(position).folderName);
        myViewholder.mTextViewCounts.setText(albumBeanList.get(position).imageCounts+"");
    }

    @Override
    public int getItemCount() {
        return albumBeanList.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextViewTitle;
        public TextView mTextViewCounts;
        public MyViewHolder(View view) {
            super(view);
            mImageView = view.findViewById(R.id.iv_directory_pic);
            mTextViewTitle = view.findViewById(R.id.tv_directory_name);
            mTextViewCounts = view.findViewById(R.id.tv_directory_nums);

        }
    }
}
