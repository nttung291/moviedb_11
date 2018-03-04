package com.framgia.moviedb.screen.popular;

import com.framgia.moviedb.model.Movie;
import com.framgia.moviedb.remote.MovieDataSource;
import com.framgia.moviedb.remote.MovieReposity;
import com.framgia.moviedb.screen.basefragment.BaseFragmentContract;
import com.framgia.moviedb.screen.basefragment.BaseFragmentPresenter;
import java.util.List;

/**
 * Listens to user actions from the UI ({@link PopularFragment}), retrieves the data and updates
 * the UI as required.
 */
public class PopularPresenter extends BaseFragmentPresenter {

    private MovieReposity mReposity;
    private int mPage = 1;

    protected PopularPresenter(BaseFragmentContract.View view, MovieReposity reposity) {
        super(view);
        mReposity = reposity;
    }

    @Override
    public void getData() {
        mReposity.getMoviePopular(mPage, new MovieDataSource.Callback<List<Movie>>() {
            @Override
            public void onStartLoading() {
                // no ops
            }

            @Override
            public void onGetSuccess(List<Movie> data) {
                mView.onGetMovieSuccess(data);
            }

            @Override
            public void onGetFailure(String message) {
                mView.onGetMovieFailure(message);
            }

            @Override
            public void onComplete() {
                // no ops
            }
        });
    }

    @Override
    public void setView(Object view) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
