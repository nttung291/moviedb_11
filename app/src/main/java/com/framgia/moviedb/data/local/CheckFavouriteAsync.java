package com.framgia.moviedb.data.local;

import android.os.AsyncTask;

import com.framgia.moviedb.data.model.Movie;

public class CheckFavouriteAsync extends AsyncTask<Movie, Void, Boolean> {
    private Callback mCallback;
    private DatabaseFavorite mDatabase;

    public CheckFavouriteAsync(Callback callback, DatabaseFavorite databaseFavorite) {
        mCallback = callback;
        mDatabase = databaseFavorite;
    }

    @Override
    protected Boolean doInBackground(Movie... movies) {
        try {
            return mDatabase.getExitsFavorite(movies[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean isExist) {
        if (isExist) {
            mCallback.moviesExisting();
        } else {
            mCallback.moviesNotExisting();
        }
    }

    public interface Callback {
        void moviesExisting();

        void moviesNotExisting();
    }
}
