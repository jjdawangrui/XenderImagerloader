package com.example.raynwang.xenderimageloader.bean;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.raynwang.xenderimageloader.utils.AlxMultiTask;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Administrator on 2016/8/25.
 */
public class AlbumBean {
    public String topImagePath;//文件夹的第一张图片路径
    public String folderName;//文件夹名
    public int imageCounts;//文件夹中的图片数
    public File albumFolder;

    @Override
    public String toString() {
        return "ImageBean{" +
                "folderName='" + folderName + '\'' +
                ", topImagePath='" + topImagePath + '\'' +
                ", imageCounts=" + imageCounts +
                '}';
    }

    /**
     * 查询手机上的所有相册
     */
    public static void getAllAlbumFromLocalStorage(final Context context, final AlbumListCallback completeCallback) {
        new AlxMultiTask<Void,Void,ArrayList<AlbumBean>>(){

            @Override
            protected ArrayList<AlbumBean> doInBackground(Void... params) {
                ArrayList<AlbumBean> albumList = new ArrayList<AlbumBean>();
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                if(context == null)return albumList;
                ContentResolver mContentResolver = context.getContentResolver();//得到内容处理者实例
                String sortOrder = MediaStore.Images.Media.DATE_MODIFIED + " desc";//设置拍摄日期为倒序
                //首先查询一共有多少张
                Cursor countCursor = mContentResolver.query(mImageUri, new String[]{"COUNT(*) "}, MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?", new String[]{"image/jpeg", "image/png"}, null);
                if(countCursor == null)return null;
                countCursor.moveToFirst();
                int photoCount = countCursor.getInt(0);//第一列
                Log.i("Alex","该手机的照片总量是::"+photoCount);
                countCursor.close();
                if(photoCount == 0)return null;
                HashMap<String,AlbumBean> albumMap = new HashMap<String, AlbumBean>();
                int index = 0;
                //获取手机的相册列表
                while (index < photoCount){//为了手机里有几万张照片OOM，500张为一组进行扫描相册列表
                    String limit = " limit "+index+",500";
                    // 只查询jpeg和png的图片
                    Cursor mCursor = mContentResolver.query(
                            mImageUri,
                            new String[]{MediaStore.Images.Media.DATA},
                            MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?",
                            new String[]{"image/jpeg", "image/png"},
                            sortOrder+limit);
                    if(mCursor == null)return null;
                    int size = mCursor.getCount();
                    index += size;
                    if(size == 0)continue;
                    for (int i = 0; i < size; i++) {//遍历全部图片
                        mCursor.moveToPosition(i);
                        String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));// 获取图片的路径
                        SelectPhotoEntity entity = new SelectPhotoEntity();
                        entity.url = path;//将图片的uri放到对象里去
                        //获取该图片的父路径名
                        String parentFolder = new File(path).getParentFile().getAbsolutePath();
                        //根据父路径名将图片放入到相册的HashMap中
                        if(albumMap.containsKey(parentFolder)){//这个相册已经被记录过了
                            AlbumBean album = albumMap.get(parentFolder);
                            album.imageCounts++;
                        }else {//这个相册没有被记录过
                            File albumFolder = new File(parentFolder);
                            if(!albumFolder.exists())continue;
                            AlbumBean mAlbumBean = new AlbumBean();
                            mAlbumBean.albumFolder = albumFolder;
                            mAlbumBean.folderName = albumFolder.getName();
                            mAlbumBean.imageCounts = 1;
                            mAlbumBean.topImagePath = path;
                            albumMap.put(parentFolder,mAlbumBean);
                        }
                    }
                    mCursor.close();
                }
                Set<String> keyset = albumMap.keySet();
                for(String albumPath:keyset)albumList.add(albumMap.get(albumPath));
                Log.i("Alex","所有图片扫描完毕,相册列表是"+albumList);
                return albumList;
            }

            @Override
            protected void onPostExecute(ArrayList<AlbumBean> albumList) {
                super.onPostExecute(albumList);
                if(albumList == null)return;
                completeCallback.onSuccess(albumList);
            }
        }.executeDependSDK();
    }

    public interface AlbumListCallback{
        void onSuccess(ArrayList<AlbumBean> albumList);
    }


    public interface AlbumPhotosCallback{
        void onSuccess(ArrayList<SelectPhotoEntity> photos);
    }
}
