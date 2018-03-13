package com.framgia.moviedb.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nttungg on 03-06-2018.
 */

public class Genres implements Parcelable {
    private int mId;
    private String mName;

    protected Genres(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
    }

    public Genres(int id, String name) {
        mId = id;
        mName = name;
    }

    public static final Creator<Genres> CREATOR = new Creator<Genres>() {
        @Override
        public Genres createFromParcel(Parcel in) {
            return new Genres(in);
        }

        @Override
        public Genres[] newArray(int size) {
            return new Genres[size];
        }
    };

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mName);
    }
}
