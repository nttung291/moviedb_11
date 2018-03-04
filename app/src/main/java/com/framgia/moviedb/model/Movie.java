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

    private Movie(int id, String title, String overview, double voteAverage,
                  String posterPath, String backdropPath, String releaseDate, String voteCount) {
        mId = id;
        mTitle = title;
        mOverview = overview;
        mVoteAverage = voteAverage;
        mPosterPath = posterPath;
        mBackdropPath = backdropPath;
        mReleaseDate = releaseDate;
        mVoteCount = voteCount;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getOverview() {
        return mOverview;
    }

    public double getVoteAverage() {
        return mVoteAverage;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getVoteCount() {
        return mVoteCount;
    }

    public static Creator<Movie> getCREATOR() {
        return CREATOR;
    }
}
