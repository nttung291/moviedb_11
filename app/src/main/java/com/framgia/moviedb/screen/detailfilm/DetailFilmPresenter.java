package com.framgia.moviedb.screen.detailfilm;

/**
 * Listens to user actions from the UI ({@link DetailFilmActivity}), retrieves the data and updates
 * the UI as required.
 */
final class DetailFilmPresenter implements DetailFilmContract.Presenter {

    private DetailFilmContract.View mView;

    public DetailFilmPresenter() {
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
}
