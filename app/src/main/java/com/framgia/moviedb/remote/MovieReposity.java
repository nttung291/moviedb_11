package com.framgia.moviedb.remote;

import com.framgia.moviedb.model.Movie;
import java.util.List;

/**
 * Created by nttungg on 3/1/18.
 */

public class MovieReposity implements MovieDataSource {

    private MovieDataSource mMovieDataSource;

    public MovieReposity(MovieDataSource movieDataSource) {
        mMovieDataSource = movieDataSource;
    }

    @Override
    public void getMoviePopular(int page, Callback<List<Movie>> callback) {
        mMovieDataSource.getMoviePopular(page, callback);
    }

    @Override
    public void getMovieNowPlaying(int page, Callback<List<Movie>> callback) {
        mMovieDataSource.getMovieNowPlaying(page, callback);
    }

    @Override
    public void getMovieTopRate(int page, Callback<List<Movie>> callback) {
        mMovieDataSource.getMovieTopRate(page, callback);
    }

    @Override
    public void getMovieUpComming(int page, Callback<List<Movie>> callback) {
        mMovieDataSource.getMovieUpComming(page, callback);
    }

}
