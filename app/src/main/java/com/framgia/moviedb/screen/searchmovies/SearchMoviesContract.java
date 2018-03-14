package com.framgia.moviedb.screen.searchmovies;

import com.framgia.moviedb.data.model.Movie;
import com.framgia.moviedb.screen.BasePresenter;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface SearchMoviesContract {
    /**
     * View.
     */
    interface View {
        void onGetMovieSuccess(List<Movie> movies);

        void onGetMovieFailure(String message);

        void onNoResult();

        void showIndicator();

        void hideIndicator();

        String getQuery();
    }

    /**
     * Presenter.
     */
    interface Presenter {

        void getData(String query, int page);

        void requestMoreData();

        void setPageIfLoadSuccess();
    }
}
