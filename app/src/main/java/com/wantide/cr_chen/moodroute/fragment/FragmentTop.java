package com.wantide.cr_chen.moodroute.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wantide.cr_chen.moodroute.R;
import com.wantide.cr_chen.moodroute.bean.MoodBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by CR_Chen on 2016/8/3.
 */
public class FragmentTop extends Fragment {

    static final String ARG_TOP = "ARG_TOP";
    MoodBean mood;

    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.title)
    TextView title;

    public static FragmentTop newInstance(MoodBean mood) {
        Bundle args = new Bundle();
        FragmentTop fragmentTop = new FragmentTop();
        args.putParcelable(ARG_TOP, mood);
        fragmentTop.setArguments(args);
        return fragmentTop;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mood = args.getParcelable(ARG_TOP);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_front, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        if (mood != null) {
            Glide.with(getActivity()).load(mood.getImage()).centerCrop().into(image);
            title.setText(mood.getName());
        }

    }
}
