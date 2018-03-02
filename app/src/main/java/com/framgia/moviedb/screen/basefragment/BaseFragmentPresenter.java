package com.framgia.moviedb.screen.basefragment;

/**
 * Listens to user actions from the UI ({@link BaseFragment}), retrieves the data and updates
 * the UI as required.
 */
public abstract class BaseFragmentPresenter implements BaseFragmentContract.Presenter {

    protected final BaseFragmentContract.View mView;

    public BaseFragmentPresenter(BaseFragmentContract.View view) {
        mView = view;
    }
}
