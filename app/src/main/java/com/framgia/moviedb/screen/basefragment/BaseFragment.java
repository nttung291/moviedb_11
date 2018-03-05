package com.framgia.moviedb.screen.basefragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.moviedb.R;
import com.framgia.moviedb.adapter.MovieAdapter;
import com.framgia.moviedb.model.Movie;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

/**
 * BaseFragment Screen.
 */
public abstract class BaseFragment extends Fragment implements BaseFragmentContract.View{

    protected abstract BaseFragmentContract.Presenter getPresenter();
    private MovieAdapter mAdapter;
    private AVLoadingIndicatorView mAVLoadingIndicatorView;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_film_mainscreen);
        mAVLoadingIndicatorView = view.findViewById(R.id.av_loading);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new MovieAdapter(getContext());
        recyclerView.setAdapter(mAdapter);
        getPresenter().getData();
        return view;
    }

    @Override
    public void onGetMovieSuccess(List<Movie> movies) {
        mAdapter.replaceData(movies);
        hideIndicator();
    }

    @Override
    public void onGetMovieFailure(String message) {

    }

    @Override
    public void showIndicator() {
        mAVLoadingIndicatorView.show();
    }

    @Override
    public void hideIndicator() {
        mAVLoadingIndicatorView.hide();
    }
}
