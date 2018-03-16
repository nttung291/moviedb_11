package com.framgia.moviedb.screen.genresdisplaymovie;

import android.content.Context;
import android.content.Intent;

import com.framgia.moviedb.data.model.Genres;
import com.framgia.moviedb.data.remote.MovieRemoteDataSource;
import com.framgia.moviedb.data.remote.MovieReposity;
import com.framgia.moviedb.screen.basedisplaymovies.DisplayMoviesActivity;
import com.framgia.moviedb.screen.basedisplaymovies.DisplayMoviesContract;
import com.framgia.moviedb.untils.Constant;

/**
 * GenresDisplayMovie Screen.
 */
public class GenresDisplayMovieActivity extends DisplayMoviesActivity {
    private Genres mGenres;

    @Override
    protected DisplayMoviesContract.Presenter getPresenter() {
        MovieReposity reposity = new MovieReposity(new MovieRemoteDataSource());
        return new GenresDisplayMoviePresenter(this, reposity);
    }

    @Override
    public int initID() {
        mGenres = getIntent().getParcelableExtra(Constant.EXTRA_GENRES);
        return mGenres.getId();
    }

    @Override
    public String initToolBarTitle() {
        return mGenres.getName();
    }

    public static Intent getGenresIntent(Context context, Genres genres) {
        Intent intent = new Intent(context, GenresDisplayMovieActivity.class);
        intent.putExtra(Constant.EXTRA_GENRES, genres);
        return intent;
    }
}
