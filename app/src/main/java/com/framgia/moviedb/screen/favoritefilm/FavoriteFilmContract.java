package com.framgia.moviedb.screen.favoritefilm;

import com.framgia.moviedb.data.model.Movie;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface FavoriteFilmContract {
    /**
     * View.
     */
    interface View {
        void onGetMovieSuccess(List<Movie> movies);

        void onRemoveFavoriteSuccess(List<Movie> movies);

        void onRemoveFavoriteFailed();
    }

    /**
     * Presenter.
     */
    interface Presenter {
        void getMovies();

        void removeMovie(int positionRemove);
    }
}
