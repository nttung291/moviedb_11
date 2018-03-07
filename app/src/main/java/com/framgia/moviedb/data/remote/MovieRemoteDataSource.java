package com.framgia.moviedb.data.remote;

import android.content.res.Resources;

import com.framgia.moviedb.BuildConfig;
import com.framgia.moviedb.R;
import com.framgia.moviedb.data.model.Actor;
import com.framgia.moviedb.data.model.Company;
import com.framgia.moviedb.data.model.Genres;
import com.framgia.moviedb.data.model.Movie;
import com.framgia.moviedb.untils.Constant;

import org.json.JSONException;

import java.util.List;

/**
 * Created by nttungg on 03/01/2018.
 */

public class MovieRemoteDataSource implements MovieDataSource {

    @Override
    public void getMoviePopular(int page, final Callback<List<Movie>> callback) {

        StringBuilder url = new StringBuilder(Constant.END_POINT_URL)
                .append(Constant.MOVIE_POPULAR_PART)
                .append("?")
                .append(Constant.API_PART)
                .append("=")
                .append(BuildConfig.API_KEY)
                .append("&")
                .append(Constant.LANGUAGE_PART)
                .append("=")
                .append(Constant.LANGUAGE)
                .append("&")
                .append(Constant.PAGE_PART)
                .append("=")
                .append(String.valueOf(page));

        loadMovieAsync(url.toString(), callback);
    }

    @Override
    public void getMovieNowPlaying(int page, Callback<List<Movie>> callback) {
        StringBuilder url = new StringBuilder(Constant.END_POINT_URL)
                .append(Constant.MOVIE_NOW_PLAYING_PART)
                .append("?")
                .append(Constant.API_PART)
                .append("=")
                .append(BuildConfig.API_KEY)
                .append("&")
                .append(Constant.LANGUAGE_PART)
                .append("=")
                .append(Constant.LANGUAGE)
                .append("&")
                .append(Constant.PAGE_PART)
                .append("=")
                .append(String.valueOf(page));

        loadMovieAsync(url.toString(), callback);
    }

    @Override
    public void getMovieTopRate(int page, Callback<List<Movie>> callback) {
        StringBuilder url = new StringBuilder(Constant.END_POINT_URL)
                .append(Constant.MOVIE_TOP_RATE_PART)
                .append("?")
                .append(Constant.API_PART)
                .append("=")
                .append(BuildConfig.API_KEY)
                .append("&")
                .append(Constant.LANGUAGE_PART)
                .append("=")
                .append(Constant.LANGUAGE)
                .append("&")
                .append(Constant.PAGE_PART)
                .append("=")
                .append(String.valueOf(page));

        loadMovieAsync(url.toString(), callback);
    }

    @Override
    public void getMovieUpComming(int page, Callback<List<Movie>> callback) {
        StringBuilder url = new StringBuilder(Constant.END_POINT_URL)
                .append(Constant.MOVIE_UP_COMMING_PART)
                .append("?")
                .append(Constant.API_PART)
                .append("=")
                .append(BuildConfig.API_KEY)
                .append("&")
                .append(Constant.LANGUAGE_PART)
                .append("=")
                .append(Constant.LANGUAGE)
                .append("&")
                .append(Constant.PAGE_PART)
                .append("=")
                .append(String.valueOf(page));

        loadMovieAsync(url.toString(), callback);
    }

    @Override
    public void getApiMovieDetail(int idMovie, int page, Callback<List<Company>> callBackCompany, Callback<List<Genres>> callBackGenres) {
        StringBuilder url = new StringBuilder(Constant.END_POINT_URL)
                .append(Constant.MOVIE_DETAIL)
                .append(idMovie)
                .append("?")
                .append(Constant.API_PART)
                .append("=")
                .append(BuildConfig.API_KEY)
                .append("&")
                .append(Constant.LANGUAGE_PART)
                .append("=")
                .append(Constant.LANGUAGE)
                .append("&")
                .append(Constant.PAGE_PART)
                .append("=")
                .append(String.valueOf(page));

        loadMovieDetailAsync(url.toString(), callBackCompany,callBackGenres);
    }

    @Override
    public void getApiActorMovie(int idMovie, Callback<List<Actor>> callback) {
        StringBuilder url = new StringBuilder(Constant.END_POINT_URL)
                .append(Constant.MOVIE_DETAIL)
                .append(idMovie)
                .append(Constant.ACTOR_CREDITS)
                .append("?")
                .append(Constant.API_PART)
                .append("=")
                .append(BuildConfig.API_KEY);

        loadActorMovieAsync(url.toString(),callback);
    }

    @Override
    public void getApiListGenres(Callback<List<Genres>> callback) {
        StringBuilder url = new StringBuilder(Constant.END_POINT_URL)
                .append(Constant.GENRE_PART)
                .append(Constant.MOVIE_PART)
                .append(Constant.LIST_PART)
                .append("?")
                .append(Constant.API_PART)
                .append("=")
                .append(BuildConfig.API_KEY)
                .append("&")
                .append(Constant.LANGUAGE_PART)
                .append("=")
                .append(Constant.LANGUAGE);

        loadGenresAsync(url.toString(),callback);
    }

    @Override
    public void getApiListMoviesGenres(int idGenres, int page, Callback<List<Movie>> callback) {
        StringBuilder url = new StringBuilder(Constant.END_POINT_URL)
                .append(Constant.GENRE_PART)
                .append("/")
                .append(idGenres)
                .append(Constant.MOVIES_PART)
                .append("?")
                .append(Constant.API_PART)
                .append("=")
                .append(BuildConfig.API_KEY)
                .append("&")
                .append(Constant.LANGUAGE_PART)
                .append("=")
                .append(Constant.LANGUAGE)
                .append("&")
                .append(Constant.PAGE_PART)
                .append("=")
                .append(String.valueOf(page))
                .append("&")
                .append(Constant.INCLUDE_ADULT)
                .append("?")
                .append(Constant.SORT_BY);
        loadMovieAsync(url.toString(), callback);
    }

    private void loadMovieAsync(String url, final Callback<List<Movie>> callback){
        new FetchDataAsync(new Callback<String>() {
            @Override
            public void onStartLoading() {
                callback.onStartLoading();
            }

            @Override
            public void onGetSuccess(String data) {
                try {
                    if (data == null) {
                        callback.onGetFailure(
                                Resources.getSystem().getString(R.string.msg_can_not_get_data));
                        return;
                    }
                    callback.onGetSuccess(ParseJson.getParseJsonMovie(data));
                } catch (JSONException e) {
                    callback.onGetFailure(e.getMessage());
                }
            }

            @Override
            public void onGetFailure(String message) {
                callback.onGetFailure(message);
            }

            @Override
            public void onComplete() {
                callback.onComplete();
            }

        }).execute(url);
    }

    private void loadMovieDetailAsync(String url, final Callback<List<Company>> callBackCompany,
                                       final Callback<List<Genres>> callBackGenres){
        new FetchDataAsync(new Callback<String>() {
            @Override
            public void onStartLoading() {
                callBackCompany.onStartLoading();
                callBackGenres.onStartLoading();
            }

            @Override
            public void onGetSuccess(String data) {
                try {
                    if (data == null) {
                        callBackCompany.onGetFailure(
                                Resources.getSystem().getString(R.string.msg_can_not_get_data));
                        callBackGenres.onGetFailure(
                                Resources.getSystem().getString(R.string.msg_can_not_get_data));
                        return;
                    }
                    callBackCompany.onGetSuccess(ParseJson.getParseJsonProductionCompanyOfMovie(data));
                    callBackGenres.onGetSuccess(ParseJson.getParseJsonGenres(data));
                } catch (JSONException e) {
                    callBackCompany.onGetFailure(e.getMessage());
                    callBackGenres.onGetFailure(e.getMessage());
                }
            }

            @Override
            public void onGetFailure(String message) {
                callBackCompany.onGetFailure(message);
                callBackGenres.onGetFailure(message);
            }

            @Override
            public void onComplete() {
                callBackCompany.onComplete();
                callBackGenres.onComplete();
            }

        }).execute(url);
    }

    private void loadActorMovieAsync(String url, final Callback<List<Actor>> callback){
        new FetchDataAsync(new Callback<String>() {
            @Override
            public void onStartLoading() {
                callback.onStartLoading();
            }

            @Override
            public void onGetSuccess(String data) {
                try {
                    if (data == null) {
                        callback.onGetFailure(
                                Resources.getSystem().getString(R.string.msg_can_not_get_data));
                        return;
                    }
                    callback.onGetSuccess(ParseJson.getParseJsonActorMovie(data));
                } catch (JSONException e) {
                    callback.onGetFailure(e.getMessage());
                }
            }

            @Override
            public void onGetFailure(String message) {
                callback.onGetFailure(message);
            }

            @Override
            public void onComplete() {
                callback.onComplete();
            }

        }).execute(url);
    }

    private void loadGenresAsync(String url, final Callback<List<Genres>> callback){
        new FetchDataAsync(new Callback<String>() {
            @Override
            public void onStartLoading() {
                callback.onStartLoading();
            }

            @Override
            public void onGetSuccess(String data) {
                try {
                    if (data == null) {
                        callback.onGetFailure(
                                Resources.getSystem().getString(R.string.msg_can_not_get_data));
                        return;
                    }
                    callback.onGetSuccess(ParseJson.getParseJsonGenres(data));
                } catch (JSONException e) {
                    callback.onGetFailure(e.getMessage());
                }
            }

            @Override
            public void onGetFailure(String message) {
                callback.onGetFailure(message);
            }

            @Override
            public void onComplete() {
                callback.onComplete();
            }

        }).execute(url);
    }
}
