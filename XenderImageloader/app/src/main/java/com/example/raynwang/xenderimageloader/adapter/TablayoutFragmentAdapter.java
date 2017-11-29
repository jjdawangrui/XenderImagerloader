package com.example.raynwang.xenderimageloader.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raynwang on 2017/11/8.
 * 该适配器适用于Report Ranking Download页面，因为都是tablayout+Viewpager
 */

public class TablayoutFragmentAdapter extends FragmentPagerAdapter{

    private List<Fragment> subFragments = new ArrayList<>();
    private List<String>   subTitles = new ArrayList<>();

    public TablayoutFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return subFragments.get(position);
    }

    @Override
    public int getCount() {
        return subFragments.size();
    }

    /**
     * 动态设置fragment
     * @param fragments
     */
    public void addAll(List<Fragment> fragments){
        this.subFragments.addAll(fragments);
    }
}
