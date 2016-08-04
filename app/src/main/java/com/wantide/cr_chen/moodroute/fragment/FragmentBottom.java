package com.wantide.cr_chen.moodroute.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wantide.cr_chen.moodroute.R;

/**
 * Created by CR_Chen on 2016/8/3.
 */
public class FragmentBottom extends Fragment {


    public static FragmentBottom newInstance() {
        return new FragmentBottom();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom, container, false);
    }

}
