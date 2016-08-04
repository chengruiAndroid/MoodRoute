package com.wantide.cr_chen.moodroute.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wantide.cr_chen.moodroute.R;
import com.wantide.cr_chen.moodroute.utils.Constants;
import com.wantide.cr_chen.moodroute.utils.DateUtil;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by CR_Chen on 2016/8/3.
 */
public class FragmentBottom extends Fragment {

    @Bind(R.id.license) TextView license;
    @Bind(R.id.title_num) TextView titleNum;
    @Bind(R.id.rount) ImageView rount;
    @Bind(R.id.time) TextView time;

    private static final String BOTTOM_POSITION = "BOTTOM_POSITION";
    private int position;

    public static FragmentBottom newInstance(int position) {
        FragmentBottom fragmentBottom = new FragmentBottom();
        Bundle args = new Bundle();
        args.putInt(BOTTOM_POSITION, position);
        fragmentBottom.setArguments(args);
        return fragmentBottom;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            position = args.getInt(BOTTOM_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom, container, false);
        ButterKnife.bind(this, view);

        license.setText(Constants.moodStr[position]);
        titleNum.setText(Constants.moodMark[position]);
        time.setText("日期: " + DateUtil.date2Str(new Date(), "MM/dd"));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
