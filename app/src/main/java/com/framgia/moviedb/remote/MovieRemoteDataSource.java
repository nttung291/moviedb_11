package com.framgia.moviedb.remote;

import android.content.res.Resources;
import com.framgia.moviedb.BuildConfig;
import com.framgia.moviedb.R;
import com.framgia.moviedb.model.Movie;
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
}
