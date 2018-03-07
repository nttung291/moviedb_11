package com.framgia.moviedb.screen.detailfilm;

import com.framgia.moviedb.data.model.Actor;
import com.framgia.moviedb.data.model.Company;
import com.framgia.moviedb.data.model.Genres;
import com.framgia.moviedb.screen.BasePresenter;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface DetailFilmContract {
    /**
     * View.
     */
    interface View {
        void onGetCompanySuccess(List<Company> companies);

        void onGetComanyFailure(String message);

        void onGetGenresSuccess(List<Genres> genres);

        void onGetGenresFailure(String message);

        void onGetActorSuccess(List<Actor> actors);

        void onGetActorFailure(String message);

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
