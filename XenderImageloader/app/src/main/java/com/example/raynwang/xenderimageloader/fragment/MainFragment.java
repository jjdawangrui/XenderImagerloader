package com.example.raynwang.xenderimageloader.fragment;

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
import com.example.raynwang.xenderimageloader.fragment.subfragment.AppFragment;
import com.example.raynwang.xenderimageloader.fragment.subfragment.FileFragment;
import com.example.raynwang.xenderimageloader.fragment.subfragment.HistoryFragment;
import com.example.raynwang.xenderimageloader.fragment.subfragment.MusicFragment;
import com.example.raynwang.xenderimageloader.fragment.subfragment.PhotoFragment;
import com.example.raynwang.xenderimageloader.fragment.subfragment.VideoFragment;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by raynwang on 2017/11/13.
 */

public class MainFragment extends Fragment {
    private static TabLayout tabLayout;
    private static ViewPager viewPager;
    private static TablayoutFragmentAdapter tablayoutFragmentAdapter;

    private static List<Fragment> subFragments = new LinkedList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_tablayout_viewpager, container, false);
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
        tabLayout.getTabAt(0).setText("HISTORY");
        tabLayout.getTabAt(1).setText("APP");
        tabLayout.getTabAt(2).setText("PHOTO");
        tabLayout.getTabAt(3).setText("MUSIC");
        tabLayout.getTabAt(4).setText("VIDEO");
        tabLayout.getTabAt(5).setText("FILE");
    }

    //初始化子fragment
    public static void initSubFragment() {
        subFragments.clear();
        subFragments.add(new HistoryFragment());
        subFragments.add(new AppFragment());
        subFragments.add(new PhotoFragment());
        subFragments.add(new MusicFragment());
        subFragments.add(new VideoFragment());
        subFragments.add(new FileFragment());
        tablayoutFragmentAdapter.addAll(subFragments);

    }
}
