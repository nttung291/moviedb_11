package com.framgia.moviedb.screen.nowplaying;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.moviedb.R;

/**
 * NowPlaying Screen.
 */
public class NowPlayingFragment extends Fragment implements NowPlayingContract.View {

    NowPlayingContract.Presenter mPresenter;

    public static NowPlayingFragment newInstance() {
        return new NowPlayingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new NowPlayingPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nowplaying, container, false);
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
