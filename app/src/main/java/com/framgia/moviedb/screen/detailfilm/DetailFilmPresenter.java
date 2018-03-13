package com.framgia.moviedb.screen.detailfilm;

import com.framgia.moviedb.data.model.Actor;
import com.framgia.moviedb.data.model.Company;
import com.framgia.moviedb.data.model.Genres;
import com.framgia.moviedb.data.model.Movie;
import com.framgia.moviedb.data.remote.MovieDataSource;
import com.framgia.moviedb.data.remote.MovieRemoteDataSource;
import com.framgia.moviedb.data.remote.MovieReposity;
import com.framgia.moviedb.untils.Constant;
import java.util.List;

/**
 * Listens to user actions from the UI ({@link DetailFilmActivity}), retrieves the data and updates
 * the UI as required.
 */
final class DetailFilmPresenter implements DetailFilmContract.Presenter {
  private MovieReposity mReposity;
  private DetailFilmContract.View mView;
  private Movie mMovie;


  public DetailFilmPresenter(Movie movie) {
    mReposity = new MovieReposity(new MovieRemoteDataSource());
    mMovie = movie;
  }

  @Override
  public void setView(DetailFilmContract.View view) {
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
    mReposity.getMovieDetail(mMovie.getId(), Constant.DEFAULT_PAGE,
            new MovieDataSource.Callback<List<Company>>() {
              @Override
              public void onStartLoading() {
              }

              @Override
              public void onGetSuccess(List<Company> data) {
                mView.onGetCompanySuccess(data);
              }

              @Override
              public void onGetFailure(String message) {
                mView.onGetComanyFailure(message);
              }

              @Override
              public void onComplete() {
              }
            }, new MovieDataSource.Callback<List<Genres>>() {

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
    mReposity.getActorMovie(mMovie.getId(), new MovieDataSource.Callback<List<Actor>>() {
      @Override
      public void onStartLoading() {
      }

      @Override
      public void onGetSuccess(List<Actor> data) {
        mView.onGetActorSuccess(data);
      }

      @Override
      public void onGetFailure(String message) {
        mView.onGetActorFailure(message);
      }

      @Override
      public void onComplete() {
      }
    });
  }
}
