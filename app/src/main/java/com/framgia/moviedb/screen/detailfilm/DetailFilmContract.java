package com.framgia.moviedb.screen.detailfilm;

import com.framgia.moviedb.screen.BasePresenter;

/**
 * This specifies the contract between the view and the presenter.
 */
interface DetailFilmContract {
    /**
     * View.
     */
    interface View {
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter<View> {
    }
}
