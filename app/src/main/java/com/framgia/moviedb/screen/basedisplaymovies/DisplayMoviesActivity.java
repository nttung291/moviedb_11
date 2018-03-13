package com.framgia.moviedb.screen.basedisplaymovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.framgia.moviedb.R;
import com.framgia.moviedb.data.model.Actor;
import com.framgia.moviedb.data.model.Company;
import com.framgia.moviedb.data.model.Genres;
import com.framgia.moviedb.data.model.Movie;
import com.framgia.moviedb.screen.EndScrollListener;
import com.framgia.moviedb.screen.LoadMoreListener;
import com.framgia.moviedb.screen.detailfilm.DetailFilmActivity;
import com.framgia.moviedb.untils.Constant;
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
    private int mTypeID;
    private DisplayMoviesAdapter mDisplayMoviesAdapter;
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private AVLoadingIndicatorView mAVLoadingIndicatorView;
    private Genres mGenres;
    private Actor mActor;
    private Company mCompany;
    private String mToolbarTitle;
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
        showIndicator();
        initData();
        initToolbar();
        mDisplayMoviesAdapter = new DisplayMoviesAdapter(this, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });
        mEndScrollListener = new EndScrollListener(this);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mDisplayMoviesAdapter);
        mRecyclerView.addOnScrollListener(mEndScrollListener);
        getPresenter().getData(Constant.DEFAULT_PAGE, mID);
    }

    public void initData() {
        mTypeID = getIntent().getIntExtra(Constant.BUNDLE_TYPE, 0);
        switch (mTypeID) {
            case Constant.ItemMovie.MOVIE_FOR_GENRE:
                mGenres = getIntent().getParcelableExtra(Constant.BUNDLE_ID_GENRES);
                mID = mGenres.getId();
                mToolbarTitle = mGenres.getName();
                break;
            case Constant.ItemMovie.MOVIE_FOR_NAME_ACTOR:
                mActor = getIntent().getParcelableExtra(Constant.BUNDLE_ID_ACTOR);
                mID = mActor.getId();
                mToolbarTitle = mActor.getName();
                break;
            case Constant.ItemMovie.MOVIE_FOR_COMPANY:
                mCompany = getIntent().getParcelableExtra(Constant.BUNDLE_ID_COMPANY);
                mID = mCompany.getId();
                mToolbarTitle = mCompany.getName();
                break;
        }
    }

    public void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(mToolbarTitle);
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
        hideIndicator();
    }

    @Override
    public void onGetMovieFailure(String message) {
        getPresenter().setPageIfLoadFailed();
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
        Intent myIntent = new Intent(this, DetailFilmActivity.class);
        myIntent.putExtra(Constant.BUNDLE_ID_MOVIE, movie); //Optional parameters
        this.startActivity(myIntent);
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
