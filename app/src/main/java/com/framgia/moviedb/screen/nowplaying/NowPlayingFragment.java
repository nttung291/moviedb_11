package com.framgia.moviedb.screen.nowplaying;

import com.framgia.moviedb.data.remote.MovieRemoteDataSource;
import com.framgia.moviedb.data.remote.MovieReposity;
import com.framgia.moviedb.screen.basefragment.BaseFragment;
import com.framgia.moviedb.screen.basefragment.BaseFragmentContract;

/**
 * NowPlaying Screen.
 */
public class NowPlayingFragment extends BaseFragment {

    public static BaseFragment newInstance() {
        return new NowPlayingFragment();
    }

    @Override
    protected BaseFragmentContract.Presenter getPresenter() {
        MovieReposity reposity = new MovieReposity(new MovieRemoteDataSource());
        return new NowPlayingPresenter(this, reposity);
    }
}
