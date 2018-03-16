package com.framgia.moviedb.screen.companydisplaymovies;

import android.content.Context;
import android.content.Intent;

import com.framgia.moviedb.data.model.Company;
import com.framgia.moviedb.data.remote.MovieRemoteDataSource;
import com.framgia.moviedb.data.remote.MovieReposity;
import com.framgia.moviedb.screen.basedisplaymovies.DisplayMoviesActivity;
import com.framgia.moviedb.screen.basedisplaymovies.DisplayMoviesContract;
import com.framgia.moviedb.untils.Constant;

/**
 * CompanyDisplayMovies Screen.
 */
public class CompanyDisplayMoviesActivity extends DisplayMoviesActivity {
    private Company mCompany;

    public static Intent getCompanyIntent(Context context, Company company) {
        Intent intent = new Intent(context, CompanyDisplayMoviesActivity.class);
        intent.putExtra(Constant.EXTRA_COMPANY, company);
        return intent;
    }

    @Override
    protected DisplayMoviesContract.Presenter getPresenter() {
        MovieReposity reposity = new MovieReposity(new MovieRemoteDataSource());
        return new CompanyDisplayMoviesPresenter(this, reposity);
    }

    @Override
    public int initID() {
        mCompany = getIntent().getParcelableExtra(Constant.EXTRA_COMPANY);
        return mCompany.getId();
    }

    @Override
    public String initToolBarTitle() {
        return mCompany.getName();
    }
}
