package com.framgia.moviedb.data.local;

import com.framgia.moviedb.data.model.Movie;

/**
 * Created by nttungg on 3/16/18.
 */

public interface DatabaseFavoriteSource {
    void addFavorite(Movie movie, AddFavouriteAsync.AddFavouriteCallback callback);

    void deleteMovieForId(Movie movie,
                          DeleteFavouriteAsync.DeleteFavouriteCallback callback);

    void getAllFavorite(GetFavouriteAsync.GetFavouriteCallback callback);

    void getExitsFavorite(Movie movie, CheckFavouriteAsync.Callback callback);
}
