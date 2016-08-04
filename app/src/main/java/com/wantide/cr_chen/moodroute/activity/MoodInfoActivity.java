package com.wantide.cr_chen.moodroute.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
public class MoodInfoActivity extends AppCompatActivity {
    private static final String EXTRA_MOOD = "EXTRA_MOOD";
    @Bind(R.id.image) ImageView image;
    @Bind(R.id.title) TextView title;

    public static Intent newInstance(Context context, MoodBean mood) {
        Intent intent = new Intent(context, MoodInfoActivity.class);
        intent.putExtra(EXTRA_MOOD, mood);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);

        MoodBean mood = getIntent().getParcelableExtra(EXTRA_MOOD);
        if (mood != null) {
            Glide.with(MoodInfoActivity.this).load(mood.getImage()).centerCrop().into(image);
            title.setText(mood.getName());
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        image = null;
        title = null;
    }
}
