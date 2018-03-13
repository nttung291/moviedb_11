package com.framgia.moviedb.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nttungg on 03-06-2018.
 */

public class Company implements Parcelable {
    private int mId;
    private String mName;

    public Company(int id, String name) {
        mId = id;
        mName = name;
    }

    protected Company(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
