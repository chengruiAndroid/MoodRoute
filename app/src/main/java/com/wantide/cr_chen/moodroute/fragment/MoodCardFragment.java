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
    private static final String ARG_POSITION = "ARG_POSITION";
    private MoodBean mood;
    private int position;

    public static MoodCardFragment newInstance(MoodBean mood, int position){
        MoodCardFragment fragment = new MoodCardFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_MOOD, mood);
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if(args != null) {
            mood = args.getParcelable(ARG_MOOD);
            position = args.getInt(ARG_POSITION);
        }
    }

    @Override
    public Fragment getFragmentTop() {
        return FragmentTop.newInstance(mood);
    }

    @Override
    public Fragment getFragmentBottom() {
        return FragmentBottom.newInstance(position);
    }

}
