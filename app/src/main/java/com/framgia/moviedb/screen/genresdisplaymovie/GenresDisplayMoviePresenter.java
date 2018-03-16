package com.framgia.moviedb.screen.genresdisplaymovie;

import com.framgia.moviedb.data.model.Movie;
import com.framgia.moviedb.data.remote.MovieDataSource;
import com.framgia.moviedb.data.remote.MovieReposity;
import com.framgia.moviedb.screen.basedisplaymovies.DisplayMoviesContract;
import com.framgia.moviedb.screen.basedisplaymovies.DisplayMoviesPresenter;

import java.util.List;

/**
 * Listens to user actions from the UI ({@link GenresDisplayMovieActivity}), retrieves the data and updates
 * the UI as required.
 */
public class GenresDisplayMoviePresenter extends DisplayMoviesPresenter {
    private MovieReposity mReposity;

    protected GenresDisplayMoviePresenter(DisplayMoviesContract.View view, MovieReposity reposity) {
        super(view);
        mReposity = reposity;
    }

    @Override
    public void getData(int page, int id) {
        mReposity.getListMoviesGenres(id, page, new MovieDataSource.Callback<List<Movie>>() {
            @Override
            public void onStartLoading() {
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
