package com.framgia.moviedb.screen.toprate;

/**
 * Listens to user actions from the UI ({@link TopRateFragment}), retrieves the data and updates
 * the UI as required.
 */
final class TopRatePresenter implements TopRateContract.Presenter {
    private static final String TAG = TopRatePresenter.class.getName();

    private final TopRateContract.View mView;

    public TopRatePresenter(TopRateContract.View view) {
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
