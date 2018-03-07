package com.framgia.moviedb.screen.genres;

import com.framgia.moviedb.data.model.Genres;
import com.framgia.moviedb.data.remote.MovieDataSource;
import com.framgia.moviedb.data.remote.MovieRemoteDataSource;
import com.framgia.moviedb.data.remote.MovieReposity;

import java.util.List;

/**
 * Listens to user actions from the UI ({@link GenresActivity}), retrieves the data and updates
 * the UI as required.
 */
final class GenresPresenter implements GenresContract.Presenter {
  private GenresContract.View mView;
  private MovieReposity mReposity;

  public GenresPresenter() {
    mReposity = new MovieReposity(new MovieRemoteDataSource());
  }

  @Override
  public void setView(GenresContract.View view) {
    mView = view;
  }

  @Override
  public void onStart() {
  }

  @Override
  public void onStop() {
  }

  @Override
  public void getData() {
    mReposity.getApiListGenres(new MovieDataSource.Callback<List<Genres>>() {
      @Override
      public void onStartLoading() {
      }

      @Override
      public void onGetSuccess(List<Genres> data) {
        mView.onGetGenresSuccess(data);
      }

      @Override
      public void onGetFailure(String message) {
        mView.onGetGenresFailure(message);
      }

      @Override
      public void onComplete() {
      }
    });
  }
}
