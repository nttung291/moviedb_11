package com.framgia.moviedb.screen.basedisplaymovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

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
 * DisplayMovies Screen.
 */
public abstract class DisplayMoviesActivity extends AppCompatActivity implements
        DisplayMoviesContract.View, DisplayMoviesAdapter.ItemClickListener
        , LoadMoreListener {

    protected abstract DisplayMoviesContract.Presenter getPresenter();

    private int mID;
    private DisplayMoviesAdapter mDisplayMoviesAdapter;
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private AVLoadingIndicatorView mAVLoadingIndicatorView;
    private EndScrollListener mEndScrollListener;

    public DisplayMoviesActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaymovies);
        mRecyclerView = findViewById(R.id.recycler_movies_display);
        mToolbar = findViewById(R.id.toolbar_display);
        mAVLoadingIndicatorView = findViewById(R.id.av_loading_display);

        mID = initID();
        showIndicator();
        initToolbar();

        mDisplayMoviesAdapter = new DisplayMoviesAdapter(this, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, Constant.SPAN_COUNT, GridLayoutManager.VERTICAL, false);
        mEndScrollListener = new EndScrollListener(this);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mDisplayMoviesAdapter);
        mRecyclerView.addOnScrollListener(mEndScrollListener);

        getPresenter().getData(Constant.DEFAULT_PAGE, mID);
    }


    public void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(initToolBarTitle());
        mToolbar.setTitleTextAppearance(this, R.style.FontTextAppearance);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onGetMovieSuccess(List<Movie> movies) {
        mDisplayMoviesAdapter.replaceData(movies);
        mEndScrollListener.setLoading(false);
        getPresenter().setPageIfLoadSuccess();
        hideIndicator();
    }

    @Override
    public void onGetMovieFailure(String message) {
        Utils.showMessageGetDataFailed(this, this);
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
    public void onItemClicked(Movie movie) {
        Intent intent = new Intent(this, DetailFilmActivity.class);
        intent.putExtra(Constant.BUNDLE_ID_MOVIE, movie); //Optional parameters
        this.startActivity(intent);
    }

    @Override
    public void requestLoadMore() {
        showIndicator();
        getPresenter().requestMoreData();
    }

    @Override
    public int getID() {
        return mID;
    }
}
