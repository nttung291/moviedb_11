package com.framgia.moviedb.data.local;


import android.os.AsyncTask;

import com.framgia.moviedb.data.model.Movie;

public class AddFavouriteAsync extends AsyncTask<Movie, Void, Boolean> {
    private AddFavouriteCallback mCallback;
    private DatabaseFavorite mDatabase;

    public AddFavouriteAsync(AddFavouriteCallback callback, DatabaseFavorite database) {
        mCallback = callback;
        mDatabase = database;
    }

    @Override
    protected Boolean doInBackground(Movie... movies) {
        try {
            mDatabase.addFavorite(movies[0]);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean isSuccess) {
        if (isSuccess) {
            mCallback.onAddSuccess();
        } else {
            mCallback.onAddFailed();
        }
    }

    public interface AddFavouriteCallback {
        void onAddSuccess();

        void onAddFailed();
    }
}
