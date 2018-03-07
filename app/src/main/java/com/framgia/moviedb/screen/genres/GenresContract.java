package com.framgia.moviedb.screen.genres;

import com.framgia.moviedb.data.model.Genres;
import com.framgia.moviedb.screen.BasePresenter;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface GenresContract {
  /**
   * View.
   */
  interface View{
    void onGetGenresSuccess(List<Genres> genres);

    void onGetGenresFailure(String message);

    void showIndicator();

    void hideIndicator();
  }

  /**
   * Presenter.
   */
  interface Presenter extends BasePresenter<View> {
    void getData();
  }
}
