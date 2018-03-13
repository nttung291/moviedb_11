package com.framgia.moviedb.screen.basedisplaymovies;

import android.support.v7.widget.Toolbar;

import com.framgia.moviedb.data.model.Movie;
import com.framgia.moviedb.screen.BasePresenter;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface DisplayMoviesContract {
    /**
     * View.
     */
    interface View {
        void onGetMovieSuccess(List<Movie> movies);

        void onGetMovieFailure(String message);

        void showIndicator();

        void hideIndicator();

        int getID();

        int initID();

        String initToolBarTitle();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {

        void getData(int page, int id);

        void requestMoreData();

        void setPageIfLoadSuccess();
    }
}
