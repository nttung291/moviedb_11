package com.framgia.moviedb.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nttungg on 03-06-2018.
 */

public class Actor implements Parcelable {
    private int mId;
    private String mName;
    private String mCharacter;
    private String mProfilePath;

    public static final Creator<Actor> CREATOR = new Creator<Actor>() {
        @Override
        public Actor createFromParcel(Parcel in) {
            return new Actor(in);
        }

        @Override
        public Actor[] newArray(int size) {
            return new Actor[size];
        }
    };

    public Actor(int id, String name, String character, String profilePath) {
        mId = id;
        mName = name;
        mCharacter = character;
        mProfilePath = profilePath;
    }

    protected Actor(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mCharacter = in.readString();
        mProfilePath = in.readString();
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getCharacter() {
        return mCharacter;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mName);
        parcel.writeString(mCharacter);
        parcel.writeString(mProfilePath);
    }
}
