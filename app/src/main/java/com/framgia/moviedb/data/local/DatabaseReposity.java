package com.framgia.moviedb.data.local;

import com.framgia.moviedb.data.model.Movie;

/**
 * Created by nttungg on 3/16/18.
 */

public class DatabaseReposity implements DatabaseFavoriteSource {
    private DatabaseFavorite mDatabase;

    public DatabaseReposity(DatabaseFavorite mDatabase) {
        this.mDatabase = mDatabase;
    }

    @Override
    public void addFavorite(Movie movie, AddFavouriteAsync.AddFavouriteCallback callback) {
        new AddFavouriteAsync(callback, mDatabase).execute(movie);
    }

    @Override
    public void deleteMovieForId(Movie movie, DeleteFavouriteAsync.DeleteFavouriteCallback callback) {
        new DeleteFavouriteAsync(callback, mDatabase).execute(movie);
    }

    @Override
    public void getAllFavorite(GetFavouriteAsync.GetFavouriteCallback callback) {
        new GetFavouriteAsync(callback, mDatabase).execute();
    }

    @Override
    public void getExitsFavorite(Movie movie, CheckFavouriteAsync.Callback callback) {
        new CheckFavouriteAsync(callback, mDatabase).execute(movie);
    }
}
