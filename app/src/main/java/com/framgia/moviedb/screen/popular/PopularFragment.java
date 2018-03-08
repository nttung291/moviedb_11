package com.framgia.moviedb.screen.popular;

import com.framgia.moviedb.data.remote.MovieRemoteDataSource;
import com.framgia.moviedb.data.remote.MovieReposity;
import com.framgia.moviedb.screen.basefragment.BaseFragment;
import com.framgia.moviedb.screen.basefragment.BaseFragmentContract;

/**
 * Popular Screen.
 */
public class PopularFragment extends BaseFragment{

    public static BaseFragment newInstance() {
        return new PopularFragment();
    }
    @Override
    protected BaseFragmentContract.Presenter getPresenter() {
        MovieReposity reposity = new MovieReposity(new MovieRemoteDataSource());
        return new PopularPresenter(this, reposity);
    }
}
