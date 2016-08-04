package com.wantide.cr_chen.moodroute.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.qslll.library.fragments.ExpandingFragment;
import com.wantide.cr_chen.moodroute.R;
import com.wantide.cr_chen.moodroute.bean.FragmentBean;
import com.wantide.cr_chen.moodroute.bean.MoodBean;
import com.wantide.cr_chen.moodroute.fragment.MoodFragment;
import com.wantide.cr_chen.moodroute.utils.DoubleClickExitDetector;
import com.wantide.cr_chen.moodroute.view.DrawerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements DrawerView.ItemClickListener, ExpandingFragment.OnExpandingClickListener{

    @Bind(R.id.toolbar) Toolbar toolbar;

    private Drawer drawer;
    private DoubleClickExitDetector detector;
    private static final String TAG = "MainActivity";
    private Fragment mCurFragment;//当前fragment
    private ArrayList<FragmentBean> mFragments;//装载所有的fragment切换页面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initTitleBar();

        DrawerView drawerView = new DrawerView();
        drawerView.setOnItemClickListener(this);
        drawer = drawerView.setupDrawerContent(MainActivity.this, toolbar);

        initMenu();

        //监听双击返回键
        detector = new DoubleClickExitDetector(MainActivity.this);
    }

    /**
     * 初始化fragment管理
     */
    private void initMenu(){
        mFragments = new ArrayList<>();
        mFragments.add(new FragmentBean(MoodFragment.getNewInstance(), "我的心情"));

        switchFragment(mFragments.get(0).getFragment(), mFragments.get(0).getTitle());
    }

    /**
     * 初始化标题栏
     */
    public void initTitleBar(){
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_18dp);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen()){
            drawer.closeDrawer();
        }else {
            if (detector.click()) {
                //杀死进程
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        }
    }

    /**
     * 切换fragment
     * @param fragment
     * @param title
     */
    private void switchFragment(Fragment fragment, String title) {
        Slide slideTransition;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//切换动画
            slideTransition = new Slide(Gravity.RIGHT);
            slideTransition.setDuration(700);
            fragment.setEnterTransition(slideTransition);
        }
        if (mCurFragment == null || !mCurFragment.getClass().getName().equals(fragment.getClass().getName())) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.replace, fragment).commit();
            mCurFragment = fragment;
            ActionBar actionBar = getSupportActionBar();
            assert actionBar != null;
            actionBar.setTitle(title);
        }
    }

    @Override
    public void itemclick(int position) {
        switch (position){
            case 0:
                switchFragment(mFragments.get(0).getFragment(), mFragments.get(0).getTitle());
                break;
            case 1:
                break;
        }

    }

    @Override
    public void onExpandingClick(View v) {
        if (mCurFragment instanceof MoodFragment){
            MoodFragment moodFragment = (MoodFragment)mCurFragment;
            View view = v.findViewById(R.id.image);
            MoodBean mood = moodFragment.getData();
            startInfoActivity(view, mood);
        }

    }

    @SuppressWarnings("unchecked")
    private void startInfoActivity(View view, MoodBean mood) {
        Activity activity = this;
        ActivityCompat.startActivity(activity,
                MoodInfoActivity.newInstance(activity, mood),
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity,
                        new Pair<>(view, getString(R.string.transition_image)))
                        .toBundle());
    }

}
