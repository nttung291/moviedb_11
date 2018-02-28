package com.framgia.moviedb.screen.toprate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.moviedb.R;

/**
 * TopRate Screen.
 */
public class TopRateFragment extends Fragment implements TopRateContract.View {

    TopRateContract.Presenter mPresenter;

    public static TopRateFragment newInstance() {
        return new TopRateFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new TopRatePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_toprate, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
        super.onStop();
    }
}
