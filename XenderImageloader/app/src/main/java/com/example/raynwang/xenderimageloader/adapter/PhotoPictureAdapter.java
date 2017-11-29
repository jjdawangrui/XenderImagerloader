package com.example.raynwang.xenderimageloader.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.raynwang.xenderimageloader.R;
import com.example.raynwang.xenderimageloader.bean.Photo;
import com.example.raynwang.xenderimageloader.fragment.subfragment.PhotoPictureFragment;
import com.example.raynwang.xenderimageloader.view.SquareImg;
import com.truizlop.sectionedrecyclerview.SectionedRecyclerViewAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by administrator on 2017/10/19.
 */

public class PhotoPictureAdapter extends SectionedRecyclerViewAdapter<PhotoPictureAdapter.MyHeadView,PhotoPictureAdapter.MyViewHolder,PhotoPictureAdapter.MyFootView>{
    public List<Photo> photos;
    Context context;
    public List<String> photoDates;
    public static int sectionCount;
    String[][] path=new String[1000][1000];

    OnItemClickListener onItemClickListener;
    OnItemLongClickListener onItemLongClickListener;
    public PhotoPictureAdapter(List<Photo> photos, List<String> photoDates, Context context) {
        this.photos = photos;
        this.context = context;
        this.photoDates = photoDates;
        sectionCount=photoDates.size();
        getPath();
    }

    @Override
    protected MyFootView onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected MyHeadView onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.headitem,parent,false);
        return new MyHeadView(view);
    }

    @Override
    protected MyViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(MyViewHolder holder, final int section, int position) {
        final MyViewHolder viewHolder=(MyViewHolder)holder;
       Glide.with(context).load(new File(path[section][position])).centerCrop().diskCacheStrategy(DiskCacheStrategy.NONE).into(viewHolder.img);
    }

    public void setOnItemClickListener(OnItemClickListener itemClick){
        this.onItemClickListener=itemClick;
    }
    public void setOnItemLongClickListener(OnItemLongClickListener itemLongClick){
        this.onItemLongClickListener=itemLongClick;
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public interface OnItemLongClickListener{
        void onItemLongClick(View view, int position);
    }


    @Override
    protected void onBindSectionFooterViewHolder(MyFootView holder, int section) {

    }

    @Override
    protected void onBindSectionHeaderViewHolder(MyHeadView holder, int section) {
            MyHeadView viewHolder=(MyHeadView)holder;
            viewHolder.dateTV.setText(photoDates.get(section));

    }

    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    @Override
    public int getSectionCount() {
        return PhotoPictureFragment.photoDates.size();
    }

    @Override
    public int getItemCount() {
        return photos.size()+photoDates.size();
    }

    @Override
    protected int getItemCountForSection(int section) {
        return getCountInsection(section);
    }


    public int  getCountInsection(int section){
        List<Photo> sectionPhotos=new ArrayList<>();
        for (int i=0;i<PhotoPictureFragment.photos.size();i++){
            if (PhotoPictureFragment.photoDates.get(section).equals(PhotoPictureFragment.photos.get(i).getDate())){
                sectionPhotos.add(PhotoPictureFragment.photos.get(i));
            }
        }

        return sectionPhotos.size();
    }

    public void getPath(){

        int count=0;
        for (int i=0;i<photoDates.size();i++){
            for (int j=0;j<getCountInsection(i);j++){
                path[i][j]=photos.get(count).getPath();
                count++;
            }
        }
    }



    class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        SquareImg img;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView.findViewById(R.id.cardView);
            img=(SquareImg)itemView.findViewById(R.id.img);
        }
    }
    class MyHeadView extends RecyclerView.ViewHolder{
        TextView dateTV;

        public MyHeadView(View itemView) {
            super(itemView);
            dateTV=(TextView) itemView.findViewById(R.id.headTV);
        }
    }
    class MyFootView extends RecyclerView.ViewHolder{
        public MyFootView(View itemView) {
            super(itemView);
        }
    }
}
