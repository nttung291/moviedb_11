package com.framgia.moviedb.screen.basefragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.framgia.moviedb.R;
import com.framgia.moviedb.data.model.Movie;
import com.framgia.moviedb.screen.EndScrollListener;
import com.framgia.moviedb.screen.LoadMoreListener;
import com.framgia.moviedb.screen.detailfilm.DetailFilmActivity;
import com.framgia.moviedb.untils.Constant;
import com.framgia.moviedb.untils.Utils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

/**
 * BaseFragment Screen.
 */
public abstract class BaseFragment extends Fragment implements BaseFragmentContract.View, LoadMoreListener
        , MovieAdapter.ItemClickListener {
    protected abstract BaseFragmentContract.Presenter getPresenter();

    private MovieAdapter mAdapter;
    private EndScrollListener mEndScrollListener;
    private AVLoadingIndicatorView mAVLoadingIndicatorView;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_film_mainscreen);
        mAVLoadingIndicatorView = view.findViewById(R.id.av_loading);
        mAdapter = new MovieAdapter(getContext(), this);
        mEndScrollListener = new EndScrollListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnScrollListener(mEndScrollListener);
        getPresenter().getData(Constant.DEFAULT_PAGE);
        setRetainInstance(true);
        return view;
    }

    @Override
    public void onGetMovieSuccess(List<Movie> movies) {
        hideIndicator();
        mAdapter.replaceData(movies);
        mEndScrollListener.setLoading(false);
    }

    @Override
    public void onGetMovieFailure(String message) {
        showIndicator();
        getPresenter().setPageIfLoadFailed();
        Utils.showMessageGetDataFailed(getActivity(), getContext());
    }

    @Override
    public void showIndicator() {
        mAVLoadingIndicatorView.show();
    }

    @Override
    public void hideIndicator() {
        mAVLoadingIndicatorView.hide();
    }

    @Override
    public void requestLoadMore() {
        showIndicator();
        getPresenter().requestMoreData();
    }

    @Override
    public void onItemClicked(Movie movie) {
        Intent myIntent = new Intent(getContext(), DetailFilmActivity.class);
        myIntent.putExtra(Constant.BUNDLE_ID_MOVIE, movie); //Optional parameters
        this.startActivity(myIntent);
    }
}
