package com.framgia.moviedb.data.remote;

import com.framgia.moviedb.data.model.Actor;
import com.framgia.moviedb.data.model.Company;
import com.framgia.moviedb.data.model.Genres;
import com.framgia.moviedb.data.model.Movie;
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

    void getApiMovieDetail(int idMovie, int page, Callback<List<Company>> callBackCompany, Callback<List<Genres>> callBackGenres);

    void getApiActorMovie(int idMovie,  Callback<List<Actor>> callback);

    void getApiListGenres (Callback<List<Genres>> callback);
}
