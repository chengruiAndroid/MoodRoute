package com.wantide.cr_chen.moodroute.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by CR_Chen on 2016/8/3.
 */
public class MoodBean implements Parcelable {
    private String name;
    private int image;
    private int color;

    public MoodBean(String name, int image, int color) {
        this.name = name;
        this.image = image;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.image);
    }

    protected MoodBean(Parcel in) {
        this.name = in.readString();
        this.image = in.readInt();
    }

    public static final Parcelable.Creator<MoodBean> CREATOR = new Parcelable.Creator<MoodBean>() {
        @Override
        public MoodBean createFromParcel(Parcel source) {
            return new MoodBean(source);
        }

        @Override
        public MoodBean[] newArray(int size) {
            return new MoodBean[size];
        }
    };
}
