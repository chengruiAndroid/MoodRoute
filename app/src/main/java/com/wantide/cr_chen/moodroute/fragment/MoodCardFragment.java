package com.wantide.cr_chen.moodroute.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.qslll.library.fragments.ExpandingFragment;
import com.wantide.cr_chen.moodroute.bean.MoodBean;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoodCardFragment extends ExpandingFragment {
    private static final String ARG_MOOD = "ARG_MOOD";
    private MoodBean mood;

    public static MoodCardFragment newInstance(MoodBean mood){
        MoodCardFragment fragment = new MoodCardFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_MOOD, mood);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if(args != null) {
            mood = args.getParcelable(ARG_MOOD);
        }
    }

    @Override
    public Fragment getFragmentTop() {
        return FragmentTop.newInstance(mood);
    }

    @Override
    public Fragment getFragmentBottom() {
        return FragmentBottom.newInstance();
    }

}
