package com.wantide.cr_chen.moodroute.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.qslll.library.ExpandingViewPagerAdapter;
import com.wantide.cr_chen.moodroute.bean.MoodBean;
import com.wantide.cr_chen.moodroute.fragment.MoodCardFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CR_Chen on 2016/8/3.
 */
public class MoodViewPagerAdapter extends ExpandingViewPagerAdapter {
    List<MoodBean> moods;

    public MoodViewPagerAdapter(FragmentManager fm) {
        super(fm);
        moods = new ArrayList<>();
    }

    public void addAll(List<MoodBean> moods){
        this.moods.addAll(moods);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        MoodBean mood = moods.get(position);
        return MoodCardFragment.newInstance(mood, position);
    }

    @Override
    public int getCount() {
        return moods.size();
    }
}
