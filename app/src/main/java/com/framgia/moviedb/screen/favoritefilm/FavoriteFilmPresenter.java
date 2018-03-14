package com.framgia.moviedb.screen.favoritefilm;

import android.content.Context;

import com.framgia.moviedb.data.local.DatabaseFavorite;
import com.framgia.moviedb.data.local.DatabaseReposity;
import com.framgia.moviedb.data.local.DeleteFavouriteAsync;
import com.framgia.moviedb.data.local.GetFavouriteAsync;
import com.framgia.moviedb.data.model.Movie;

import java.util.List;

/**
 * Listens to user actions from the UI ({@link FavoriteFilmActivity}), retrieves the data and updates
 * the UI as required.
 */
final class FavoriteFilmPresenter implements FavoriteFilmContract.Presenter {

    private FavoriteFilmContract.View mView;
    private DatabaseReposity mDatabaseReposite;
    private List<Movie> mMovies;

    public FavoriteFilmPresenter(FavoriteFilmContract.View view, Context context) {
        mView = view;
        mDatabaseReposite = new DatabaseReposity(new DatabaseFavorite(context));
    }


    @Override
    public void getMovies() {
        mDatabaseReposite.getAllFavorite(new GetFavouriteAsync.GetFavouriteCallback() {
            @Override
            public void onGetMoviesSuccess(List<Movie> movies) {
                mView.onGetMovieSuccess(movies);
                mMovies = movies;
            }

            @Override
            public void onGetMoviesFailed() {
            }
        });

    }

    @Override
    public void removeMovie(final int positionRemove) {
        mDatabaseReposite.deleteMovieForId(mMovies.get(positionRemove), new DeleteFavouriteAsync.DeleteFavouriteCallback() {
            @Override
            public void onDeleteSuccess() {
                mMovies.remove(positionRemove);
                mView.onRemoveFavoriteSuccess(mMovies);
            }

            @Override
            public void onDeleteFailed() {
                mView.onRemoveFavoriteFailed();
            }
        });
    }
}
