package com.framgia.moviedb.screen.main;

import com.framgia.moviedb.screen.BasePresenter;

/**
 * This specifies the contract between the view and the presenter.
 */
interface MainContract {
    /**
     * View.
     */
    interface View  {
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter<View> {
        void setView(View view);

        void onStart();

        void onStop();
    }
}
