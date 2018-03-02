package com.framgia.moviedb.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nttungg on 03-02-2018.
 */

public class Movie implements Parcelable {
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
    private int mId;
    private String mTitle;
    private String mOverview;
    private double mVoteAverage;
    private String mPosterPath;
    private String mBackdropPath;
    private String mReleaseDate;

    public Movie(int id, String title, String overview, double voteAverage, String posterPath,
            String backdropPath, String releaseDate) {
        mId = id;
        mTitle = title;
        mOverview = overview;
        mVoteAverage = voteAverage;
        mPosterPath = posterPath;
        mBackdropPath = backdropPath;
        mReleaseDate = releaseDate;
    }

    protected Movie(Parcel in) {
        mId = in.readInt();
        mTitle = in.readString();
        mOverview = in.readString();
        mVoteAverage = in.readDouble();
        mPosterPath = in.readString();
        mBackdropPath = in.readString();
        mReleaseDate = in.readString();
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mTitle);
        dest.writeString(mOverview);
        dest.writeDouble(mVoteAverage);
        dest.writeString(mPosterPath);
        dest.writeString(mBackdropPath);
        dest.writeString(mReleaseDate);
    }
}
