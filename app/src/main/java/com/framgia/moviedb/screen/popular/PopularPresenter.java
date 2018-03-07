package com.framgia.moviedb.screen.popular;

import com.framgia.moviedb.data.model.Movie;
import com.framgia.moviedb.data.remote.MovieDataSource;
import com.framgia.moviedb.data.remote.MovieReposity;
import com.framgia.moviedb.screen.basefragment.BaseFragmentContract;
import com.framgia.moviedb.screen.basefragment.BaseFragmentPresenter;
import java.util.List;

/**
 * Listens to user actions from the UI ({@link PopularFragment}), retrieves the data and updates
 * the UI as required.
 */
public class PopularPresenter extends BaseFragmentPresenter {
    private MovieReposity mReposity;

    protected PopularPresenter(BaseFragmentContract.View view, MovieReposity reposity) {
        super(view);
        mReposity = reposity;
    }

    @Override
    public void getData(int page) {
        mReposity.getMoviePopular(page, new MovieDataSource.Callback<List<Movie>>() {
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
