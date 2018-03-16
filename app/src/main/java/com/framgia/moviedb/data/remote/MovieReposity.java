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
    public void getMovieDetail(int idMovie, int page, Callback<List<Company>> callBackCompany, Callback<List<Genres>> callBackGenres) {
        mMovieDataSource.getMovieDetail(idMovie, page, callBackCompany, callBackGenres);
    }

    @Override
    public void getActorMovie(int idMovie, Callback<List<Actor>> callback) {
        mMovieDataSource.getActorMovie(idMovie, callback);
    }

    @Override
    public void getListGenres(Callback<List<Genres>> callback) {
        mMovieDataSource.getListGenres(callback);
    }

    @Override
    public void getListMoviesGenres(int idGenres, int page, Callback<List<Movie>> callback) {
        mMovieDataSource.getListMoviesGenres(idGenres, page, callback);
    }

    @Override
    public void getListMoviesActor(int idActor, int page, Callback<List<Movie>> callback) {
        mMovieDataSource.getListMoviesActor(idActor, page, callback);
    }

    @Override
    public void getListMoviesCompany(int idCompany, int page, Callback<List<Movie>> callback) {
        mMovieDataSource.getListMoviesCompany(idCompany, page, callback);
    }

    @Override
    public void getMoviesSearch(String query, int page, Callback<List<Movie>> callback) {
        mMovieDataSource.getMoviesSearch(query, page, callback);
    }

    @Override
    public void getKeyMovieYoutube(int idMovie, Callback<String> callback) {
        mMovieDataSource.getKeyMovieYoutube(idMovie, callback);
    }


}
