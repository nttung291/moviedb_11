package com.framgia.moviedb.screen.basefragment;

import com.framgia.moviedb.model.Movie;
import com.framgia.moviedb.screen.BasePresenter;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface BaseFragmentContract {
    /**
     * View.
     */
    interface View{
        void onGetMovieSuccess(List<Movie> movies);

        void onGetMovieFailure(String message);

    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getData();
    }
}
