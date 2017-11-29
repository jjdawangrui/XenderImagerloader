package com.example.raynwang.xenderimageloader.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.raynwang.xenderimageloader.R;
import com.example.raynwang.xenderimageloader.fragment.MainFragment;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment mainFragment = new MainFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

//        if (mainFragment.isAdded()) {//判断当前是否已经添加到容器中
//            //如果已经添加到容器中，直接显示
//            fragmentTransaction.show(mainFragment);
//        } else {
//            //将fragment添加到容器中
//            fragmentTransaction.add(R.id.fl_container, mainFragment);
//        }
//        fragmentTransaction.commitNowAllowingStateLoss();

        fragmentTransaction.add(R.id.fl_container, mainFragment);
        fragmentTransaction.show(mainFragment);
        fragmentTransaction.commitNowAllowingStateLoss();
    }
}
