package com.framgia.moviedb.data.remote;

import com.framgia.moviedb.data.model.Actor;
import com.framgia.moviedb.data.model.Company;
import com.framgia.moviedb.data.model.Genres;
import com.framgia.moviedb.data.model.Movie;

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

    @Override
    public void getApiMovieDetail(int idMovie, int page, Callback<List<Company>> callBackCompany, Callback<List<Genres>> callBackGenres) {
        mMovieDataSource.getApiMovieDetail(idMovie,page,callBackCompany,callBackGenres);
    }

    @Override
    public void getApiActorMovie(int idMovie, Callback<List<Actor>> callback) {
        mMovieDataSource.getApiActorMovie(idMovie, callback);
    }

    @Override
    public void getApiListGenres(Callback<List<Genres>> callback) {
        mMovieDataSource.getApiListGenres(callback);
    }

    @Override
    public void getApiListMoviesGenres(int idGenres, int page, Callback<List<Movie>> callback) {
        mMovieDataSource.getApiListMoviesGenres(idGenres, page, callback);
    }
}
