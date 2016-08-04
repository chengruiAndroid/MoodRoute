package com.wantide.cr_chen.moodroute.fragment;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.transition.Explode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.qslll.library.ExpandingPagerFactory;
import com.qslll.library.fragments.ExpandingFragment;
import com.wantide.cr_chen.moodroute.R;
import com.wantide.cr_chen.moodroute.adapter.MoodViewPagerAdapter;
import com.wantide.cr_chen.moodroute.bean.MoodBean;
import com.wantide.cr_chen.moodroute.utils.ColorChange;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoodFragment extends Fragment {

    @Bind(R.id.view_pager) ViewPager viewPager;
    @Bind(R.id.content_view) RelativeLayout contentView;

    private static final String TAG = "MoodFragment";
    private MoodBean mCUrMood;
    private MoodBean mNextMood;

    public static MoodFragment getNewInstance() {
        return new MoodFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mood, container, false);
        ButterKnife.bind(this, view);

        init();
        return view;
    }

    /**
     * 初始化操作
     */
    private void init() {
        setupWindowAnimations();

        MoodViewPagerAdapter adapter = new MoodViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addAll(generateTravelList());
        viewPager.setAdapter(adapter);
        ExpandingPagerFactory.setupViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ExpandingFragment expandingFragment = ExpandingPagerFactory.getCurrentFragment(viewPager);
                if (expandingFragment != null && expandingFragment.isOpenend()) {
                    expandingFragment.close();
                }

                Log.d(TAG, "onPageScrolled() called with: " + "position = [" + position + "], positionOffset = [" + positionOffset + "], positionOffsetPixels = [" + positionOffsetPixels + "]");
                contentView.setBackgroundColor(ColorChange.caculateColor(getResources().getColor(mCUrMood.getColor()), getResources().getColor(mNextMood.getColor()), positionOffset));
            }

            @Override
            public void onPageSelected(int position) {
                mCUrMood = generateTravelList().get(position);
                mNextMood = generateTravelList().get(position + 1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private List<MoodBean> generateTravelList() {
        List<MoodBean> moods = new ArrayList<>();
        moods.add(new MoodBean("烦躁", R.drawable.fidget, R.color.card_fidget));
        moods.add(new MoodBean("害怕", R.drawable.afraid, R.color.card_afraid));
        moods.add(new MoodBean("紧张", R.drawable.tense, R.color.card_tense));
        moods.add(new MoodBean("沮丧", R.drawable.depression, R.color.card_depression));
        moods.add(new MoodBean("开心", R.drawable.happy, R.color.card_happy));
        moods.add(new MoodBean("满足", R.drawable.satisfy, R.color.card_satisfy));
        moods.add(new MoodBean("平静", R.drawable.calm, R.color.card_calm));
        moods.add(new MoodBean("忧伤", R.drawable.sad, R.color.card_sad));

        mCUrMood = moods.get(0);
        mNextMood = moods.get(1);//初始化下一mode
        return moods;
    }

    /**
     * 获取当前bean
     *
     * @return
     */
    public MoodBean getData() {
        return generateTravelList().get(viewPager.getCurrentItem());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        Explode slideTransition = new Explode();
        getActivity().getWindow().setReenterTransition(slideTransition);
        getActivity().getWindow().setExitTransition(slideTransition);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
