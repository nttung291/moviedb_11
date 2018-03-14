package com.framgia.moviedb.data.local;


import android.os.AsyncTask;

import com.framgia.moviedb.data.model.Movie;

public class DeleteFavouriteAsync extends AsyncTask<Movie, Void, Boolean> {
    private DeleteFavouriteCallback mCallback;
    private DatabaseFavorite mDatabase;

    public DeleteFavouriteAsync(
            DeleteFavouriteCallback callback, DatabaseFavorite databaseFavorite) {
        mCallback = callback;
        mDatabase = databaseFavorite;
    }

    @Override
    protected Boolean doInBackground(Movie... movies) {
        try {
            return mDatabase.deleteMovieForId(movies[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean isSuccess) {
        if (isSuccess) {
            mCallback.onDeleteSuccess();
        } else {
            mCallback.onDeleteFailed();
        }
    }

    public interface DeleteFavouriteCallback {
        void onDeleteSuccess();

        void onDeleteFailed();
    }
}
