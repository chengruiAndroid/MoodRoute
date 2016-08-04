package com.wantide.cr_chen.moodroute;

import android.app.Application;
import android.content.Context;

/**
 * Created by CR_Chen on 2016/8/3.
 */
public class MyApplication extends Application{
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
    }

    /*获取全局context*/
    public static Context getAppContext(){
        return mContext;
    }
}
