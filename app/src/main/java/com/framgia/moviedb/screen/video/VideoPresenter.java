package com.framgia.moviedb.screen.video;

import com.framgia.moviedb.data.model.Movie;
import com.framgia.moviedb.data.remote.MovieDataSource;
import com.framgia.moviedb.data.remote.MovieRemoteDataSource;
import com.framgia.moviedb.data.remote.MovieReposity;
import com.framgia.moviedb.untils.Constant;

import java.util.List;

/**
 * Listens to user actions from the UI ({@link VideoActivity}), retrieves the data and updates
 * the UI as required.
 */
final class VideoPresenter implements VideoContract.Presenter {

    private VideoContract.View mView;
    private MovieReposity mReposity;
    private int mPage;

    public VideoPresenter(VideoContract.View view) {
        mView = view;
        mReposity = new MovieReposity(new MovieRemoteDataSource());
        mPage = Constant.DEFAULT_PAGE;
    }

    @Override
    public void getData(int movieID) {
        mReposity.getKeyMovieYoutube(movieID, new MovieDataSource.Callback<String>() {
            @Override
            public void onStartLoading() {

            }

            @Override
            public void onGetSuccess(String data) {
                mView.onGetMovieSuccess(data);
            }

            @Override
            public void onGetFailure(String message) {
                mView.onGetMoviesFailure(message);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getSuggestMovie(int page, int id) {
        mReposity.getListMoviesGenres(id, page, new MovieDataSource.Callback<List<Movie>>() {
            @Override
            public void onStartLoading() {
            }

            @Override
            public void onGetSuccess(List<Movie> data) {
                mView.onGetSugesstionSuccess(data);
            }

            @Override
            public void onGetFailure(String message) {
                mView.onGetSugesstionFailure(message);
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    public void requestMoreData() {
        getSuggestMovie(mPage, mView.getID());
    }

    @Override
    public void setPageIfLoadSuccess() {
        mPage++;
    }
}
