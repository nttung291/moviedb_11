package com.framgia.moviedb.screen.upcoming;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.moviedb.R;

/**
 * UpComing Screen.
 */
public class UpComingFragment extends Fragment implements UpComingContract.View {

    UpComingContract.Presenter mPresenter;

    public static UpComingFragment newInstance() {
        return new UpComingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new UpComingPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_upcoming, container, false);
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
