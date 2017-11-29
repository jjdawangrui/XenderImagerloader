package com.example.raynwang.xenderimageloader.fragment.subfragment;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.raynwang.xenderimageloader.R;
import com.example.raynwang.xenderimageloader.adapter.PhotoAlbumAdapter;
import com.example.raynwang.xenderimageloader.adapter.PhotoPictureAdapter;
import com.example.raynwang.xenderimageloader.bean.AlbumBean;
import com.example.raynwang.xenderimageloader.bean.Photo;
import com.truizlop.sectionedrecyclerview.SectionedSpanSizeLookup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.raynwang.xenderimageloader.fragment.subfragment.PhotoPictureFragment.photos;

/**
 * Created by raynwang on 2017/11/13.
 */

public class PhotoAlbumFragment extends android.support.v4.app.Fragment {
    private RecyclerView mRecyclerView;
    private PhotoAlbumAdapter adapter;

    public static List<AlbumBean> albumBeanList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_photo_picture,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //查找并设置手机上的所有相册
        AlbumBean.getAllAlbumFromLocalStorage(getContext(), new AlbumBean.AlbumListCallback() {
            @Override
            public void onSuccess(ArrayList<AlbumBean> result) {
                albumBeanList.addAll(result);
                adapter = new PhotoAlbumAdapter(albumBeanList,getContext());
                mRecyclerView.setAdapter(adapter);
            }
        });

    }

//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 1:
//                    Bundle bundle = msg.getData();
//                    photos = (ArrayList<Photo>) bundle.getParcelableArrayList("photos").get(0);
//                    photoDates = (ArrayList<String>) bundle.getParcelableArrayList("photos").get(1);
//
//                    adapter = new PhotoAlbumAdapter(albumBeanList);
//
//                    GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
//                    SectionedSpanSizeLookup lookup = new SectionedSpanSizeLookup(adapter, manager);
//                    manager.setSpanSizeLookup(lookup);
//                    mRecyclerView.setLayoutManager(manager);
//                    mRecyclerView.setAdapter(adapter);
//                    adapter.setOnItemClickListener(new PhotoPictureAdapter.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(View view, int position) {
//                            Toast.makeText(getContext(),position+": "+photos.get(position).getPath(),Toast.LENGTH_LONG).show();
//                        }
//                    });
//            }
//        }
//    };
//
//    public void getPhotos() {
//        new Thread() {
//            @Override
//            public void run() {
//                List<Photo> list = new ArrayList<Photo>();
//                List<String> list1 = new ArrayList<String>();
//                super.run();
//                Cursor cursor = resolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Images.Media.DATE_TAKEN + " desc");
//                if (cursor != null && cursor.moveToFirst()) {
//                    do {
//                        String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
//                        long date = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN));
//                        long id=cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
//                        String name=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));
//                        String dec=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
//                        Date date1 = new Date(date);
//                        String Sdate = format.format(date1);
//
//                        Photo photo = new Photo(path, Sdate,id,dec,name);
//                        if (!list1.contains(Sdate)) {
//                            list1.add(Sdate);
//                        }
//                        list.add(photo);
//                    } while (cursor.moveToNext());
//                    cursor.close();
//                    Message msg = new Message();
//                    msg.what = 1;
//                    Bundle bundle = new Bundle();
//                    ArrayList Alist = new ArrayList();
//                    Alist.add(list);
//                    Alist.add(list1);
//                    bundle.putParcelableArrayList("photos", Alist);
//
//                    msg.setData(bundle);
//                    handler.sendMessage(msg);
//                }
//            }
//        }.start();
//    }
}
