package com.framgia.moviedb.remote;

import com.framgia.moviedb.model.Movie;
import java.util.List;

/**
 * Created by nttungg on 3/1/18.
 */

public interface MovieDataSource {
    interface Callback<T> {
        void onStartLoading();

        void onGetSuccess(T data);

        void onGetFailure(String message);

        void onComplete();

    }

    void getMoviePopular(int page, Callback<List<Movie>> callback);

    void getMovieNowPlaying(int page, Callback<List<Movie>> callback);

    void getMovieTopRate(int page, Callback<List<Movie>> callback);

    void getMovieUpComming(int page, Callback<List<Movie>> callback);

}
