package com.framgia.moviedb.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nttungg on 03-02-2018.
 */

public class Movie implements Parcelable {
    private int mId;
    private String mTitle;
    private String mOverview;
    private double mVoteAverage;
    private String mPosterPath;
    private String mBackdropPath;
    private String mReleaseDate;
    private String mVoteCount;

    protected Movie(Parcel in) {
        mId = in.readInt();
        mTitle = in.readString();
        mOverview = in.readString();
        mVoteAverage = in.readDouble();
        mPosterPath = in.readString();
        mBackdropPath = in.readString();
        mReleaseDate = in.readString();
        mVoteCount = in.readString();
    }

    public Movie(int mId, String mTitle, String mOverview, double mVoteAverage, String mPosterPath,
                 String mBackdropPath, String mReleaseDate, String mVoteCount) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mOverview = mOverview;
        this.mVoteAverage = mVoteAverage;
        this.mPosterPath = mPosterPath;
        this.mBackdropPath = mBackdropPath;
        this.mReleaseDate = mReleaseDate;
        this.mVoteCount = mVoteCount;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mTitle);
        parcel.writeString(mOverview);
        parcel.writeDouble(mVoteAverage);
        parcel.writeString(mPosterPath);
        parcel.writeString(mBackdropPath);
        parcel.writeString(mReleaseDate);
        parcel.writeString(mVoteCount);
    }

    public int getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmOverview() {
        return mOverview;
    }

    public double getmVoteAverage() {
        return mVoteAverage;
    }

    public String getmPosterPath() {
        return mPosterPath;
    }

    public String getmBackdropPath() {
        return mBackdropPath;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public String getmVoteCount() {
        return mVoteCount;
    }

    public static Creator<Movie> getCREATOR() {
        return CREATOR;
    }
}
