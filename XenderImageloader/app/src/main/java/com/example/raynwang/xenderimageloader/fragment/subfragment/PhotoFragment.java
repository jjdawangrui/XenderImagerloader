package com.example.raynwang.xenderimageloader.fragment.subfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.raynwang.xenderimageloader.R;
import com.example.raynwang.xenderimageloader.adapter.TablayoutFragmentAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by raynwang on 2017/11/13.
 */

public class PhotoFragment extends android.support.v4.app.Fragment {
    private static TabLayout tabLayout;
    private static ViewPager viewPager;
    private static TablayoutFragmentAdapter tablayoutFragmentAdapter;

    private static List<Fragment> subFragments = new LinkedList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_subfragment_photo,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tablayoutFragmentAdapter = new TablayoutFragmentAdapter(getChildFragmentManager());
        initSubFragment();
        viewPagerBindTabLayout();
        initSubTitle();
    }

    public static void viewPagerBindTabLayout() {
        viewPager.setAdapter(tablayoutFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    //初始化子标题
    public static void initSubTitle() {
        tabLayout.getTabAt(0).setText("picture");
        tabLayout.getTabAt(1).setText("album");
    }

    //初始化子fragment
    public static void initSubFragment() {
        subFragments.clear();
        subFragments.add(new PhotoPictureFragment());
        subFragments.add(new PhotoAlbumFragment());
        tablayoutFragmentAdapter.addAll(subFragments);

    }
}
