package com.framgia.moviedb.screen.searchmovies;

import com.framgia.moviedb.data.model.Movie;
import com.framgia.moviedb.data.remote.MovieDataSource;
import com.framgia.moviedb.data.remote.MovieRemoteDataSource;
import com.framgia.moviedb.data.remote.MovieReposity;
import com.framgia.moviedb.untils.Constant;

import java.util.List;

/**
 * Listens to user actions from the UI ({@link SearchMoviesActivity}), retrieves the data and updates
 * the UI as required.
 */
public class SearchMoviesPresenter implements SearchMoviesContract.Presenter {

    private int mPage;
    private MovieReposity mReposity;
    protected SearchMoviesContract.View mView;

    public SearchMoviesPresenter(SearchMoviesContract.View view) {
        mView = view;
        mPage = Constant.DEFAULT_PAGE;
        mReposity = new MovieReposity(new MovieRemoteDataSource());
    }

    @Override
    public void getData(String query, int page) {
        mReposity.getMoviesSearch(query, page, new MovieDataSource.Callback<List<Movie>>() {
            @Override
            public void onStartLoading() {
            }

            @Override
            public void onGetSuccess(List<Movie> data) {
                if (data.size() != 0) {
                    mView.onGetMovieSuccess(data);
                } else {
                    mView.onNoResult();
                }
            }

            @Override
            public void onGetFailure(String message) {
                mView.onGetMovieFailure(message);
            }

            @Override
            public void onComplete() {
            }
        });

    }

    @Override
    public void requestMoreData() {
        getData(mView.getQuery(), mPage);
    }

    @Override
    public void setPageIfLoadSuccess() {
        mPage++;
    }
}
