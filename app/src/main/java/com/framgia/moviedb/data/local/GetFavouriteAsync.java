package com.framgia.moviedb.data.local;


import android.os.AsyncTask;
import android.util.Log;

import com.framgia.moviedb.data.model.Movie;

import java.util.List;

public class GetFavouriteAsync extends AsyncTask<Void, Void, List<Movie>> {
    private GetFavouriteCallback mCallback;
    private DatabaseFavorite mDatabase;

    public GetFavouriteAsync(GetFavouriteCallback callback, DatabaseFavorite databaseFavorite) {
        mCallback = callback;
        mDatabase = databaseFavorite;
    }

    @Override
    protected List<Movie> doInBackground(Void... voids) {
        List<Movie> movies = null;
        try {
            movies = mDatabase.getAllFavorite();
        } catch (Exception e) {
            mCallback.onGetMoviesFailed();
        }
        return movies;
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        if (mCallback == null) return;
        if (movies == null || movies.size() == 0) {
            mCallback.onGetMoviesFailed();
        } else {
            mCallback.onGetMoviesSuccess(movies);
        }
    }

    public interface GetFavouriteCallback {
        void onGetMoviesSuccess(List<Movie> movies);

        void onGetMoviesFailed();
    }
}
