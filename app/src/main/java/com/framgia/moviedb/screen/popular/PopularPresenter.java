package com.framgia.moviedb.screen.popular;

/**
 * Listens to user actions from the UI ({@link PopularFragment}), retrieves the data and updates
 * the UI as required.
 */
final class PopularPresenter implements PopularContract.Presenter {
    private static final String TAG = PopularPresenter.class.getName();

    private final PopularContract.View mView;

    public PopularPresenter(PopularContract.View view) {
        mView = view;
    }

    @Override
    public void setView(Object view) {

    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
