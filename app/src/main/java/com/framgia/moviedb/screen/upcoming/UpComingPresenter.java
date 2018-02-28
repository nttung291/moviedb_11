package com.framgia.moviedb.screen.upcoming;

/**
 * Listens to user actions from the UI ({@link UpComingFragment}), retrieves the data and updates
 * the UI as required.
 */
final class UpComingPresenter implements UpComingContract.Presenter {
    private static final String TAG = UpComingPresenter.class.getName();

    private final UpComingContract.View mView;

    public UpComingPresenter(UpComingContract.View view) {
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
