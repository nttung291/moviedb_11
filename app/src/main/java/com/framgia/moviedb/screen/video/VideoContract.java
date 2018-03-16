package com.framgia.moviedb.screen.video;

import com.framgia.moviedb.data.model.Movie;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface VideoContract {
    /**
     * View.
     */
    interface View {
        void onGetMovieSuccess(String key);

        void onGetMoviesFailure(String message);

        void onGetSugesstionSuccess(List<Movie> movies);

        void onGetSugesstionFailure(String message);

        int getID();
    }

    /**
     * Presenter.
     */
    interface Presenter{
        void getData(int movieID);

        void getSuggestMovie(int page, int id);

        void requestMoreData();

        void setPageIfLoadSuccess();
    }
}
