package com.framgia.moviedb.screen.genresdisplaymovie;

import com.framgia.moviedb.data.remote.MovieRemoteDataSource;
import com.framgia.moviedb.data.remote.MovieReposity;
import com.framgia.moviedb.screen.basedisplaymovies.DisplayMoviesActivity;
import com.framgia.moviedb.screen.basedisplaymovies.DisplayMoviesContract;

/**
 * GenresDisplayMovie Screen.
 */
public class GenresDisplayMovieActivity extends DisplayMoviesActivity {

	public static DisplayMoviesActivity newInstance() {
		return new GenresDisplayMovieActivity();
	}

	@Override
	protected DisplayMoviesContract.Presenter getPresenter() {
		MovieReposity reposity = new MovieReposity(new MovieRemoteDataSource());
		return new GenresDisplayMoviePresenter(this, reposity);
	}
}
