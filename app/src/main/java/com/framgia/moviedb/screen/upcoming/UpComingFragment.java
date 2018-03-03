package com.framgia.moviedb.screen.upcoming;

import com.framgia.moviedb.remote.MovieRemoteDataSource;
import com.framgia.moviedb.remote.MovieReposity;
import com.framgia.moviedb.screen.basefragment.BaseFragment;
import com.framgia.moviedb.screen.basefragment.BaseFragmentContract;

/**
 * UpComing Screen.
 */
public class UpComingFragment extends BaseFragment{

    public static BaseFragment newInstance() {
        return new UpComingFragment();
    }
    @Override
    protected BaseFragmentContract.Presenter getPresenter() {
        MovieReposity reposity = new MovieReposity(new MovieRemoteDataSource());
        return new UpComingPresenter(this, reposity);
    }
}
