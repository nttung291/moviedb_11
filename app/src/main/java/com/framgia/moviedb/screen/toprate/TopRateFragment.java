package com.framgia.moviedb.screen.toprate;

import com.framgia.moviedb.data.remote.MovieRemoteDataSource;
import com.framgia.moviedb.data.remote.MovieReposity;
import com.framgia.moviedb.screen.basefragment.BaseFragment;
import com.framgia.moviedb.screen.basefragment.BaseFragmentContract;

/**
 * TopRate Screen.
 */
public class TopRateFragment extends BaseFragment {

    public static BaseFragment newInstance() {
        return new TopRateFragment();
    }
    @Override
    protected BaseFragmentContract.Presenter getPresenter() {
        MovieReposity reposity = new MovieReposity(new MovieRemoteDataSource());
        return new TopRatePresenter(this, reposity);
    }
}
