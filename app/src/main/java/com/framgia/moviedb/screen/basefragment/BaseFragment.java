package com.framgia.moviedb.screen.basefragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.moviedb.R;


/**
 * BaseFragment Screen.
 */
public abstract class BaseFragment extends Fragment implements BaseFragmentContract.View {

    protected abstract BaseFragmentContract.Presenter getPresenter();

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        return view;
    }

}
