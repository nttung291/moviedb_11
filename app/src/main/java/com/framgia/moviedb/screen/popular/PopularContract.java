package com.framgia.moviedb.screen.popular;


import com.framgia.moviedb.screen.BasePresenter;

/**
 * This specifies the contract between the view and the presenter.
 */
interface PopularContract {
    /**
     * View.
     */
    interface View  {
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void onStart();

        void onStop();
    }
}
