package com.framgia.moviedb.screen.nowplaying;

/**
 * Listens to user actions from the UI ({@link NowPlayingFragment}), retrieves the data and updates
 * the UI as required.
 */
final class NowPlayingPresenter implements NowPlayingContract.Presenter {
    private static final String TAG = NowPlayingPresenter.class.getName();

    private final NowPlayingContract.View mView;

    public NowPlayingPresenter(NowPlayingContract.View view) {
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
