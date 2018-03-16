package com.framgia.moviedb.screen.searchmovies;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.framgia.moviedb.R;
import com.framgia.moviedb.data.model.Movie;
import com.framgia.moviedb.screen.EndScrollListener;
import com.framgia.moviedb.screen.LoadMoreListener;
import com.framgia.moviedb.screen.basedisplaymovies.DisplayMoviesAdapter;
import com.framgia.moviedb.screen.detailfilm.DetailFilmActivity;
import com.framgia.moviedb.untils.Constant;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

/**
 * SearchMovies Screen.
 */
public class SearchMoviesActivity extends AppCompatActivity implements SearchMoviesContract.View
        , DisplayMoviesAdapter.ItemClickListener
        , LoadMoreListener, SearchView.OnQueryTextListener {

    private SearchMoviesContract.Presenter mPresenter;
    private String mQuery;
    private DisplayMoviesAdapter mDisplayMoviesAdapter;
    private RecyclerView mRecyclerView;
    private TextView mTextViewResult;
    private Toolbar mToolbar;
    private AVLoadingIndicatorView mAVLoadingIndicatorView;
    private EndScrollListener mEndScrollListener;
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchmovies);
        mPresenter = new SearchMoviesPresenter(this);
        mTextViewResult = findViewById(R.id.text_result_search);
        mRecyclerView = findViewById(R.id.recycler_movies_seacrh);
        mToolbar =  findViewById(R.id.toolbar_seacrh);
        mAVLoadingIndicatorView = findViewById(R.id.av_loading_seacrh);

        initToolbar();
        showIndicator();
        mQuery = getIntent().getStringExtra(Constant.BUNDLE_SEARCH);

        mDisplayMoviesAdapter = new DisplayMoviesAdapter(this, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, Constant.SPAN_COUNT, GridLayoutManager.VERTICAL, false);
        mEndScrollListener = new EndScrollListener(this);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mDisplayMoviesAdapter);
        mRecyclerView.addOnScrollListener(mEndScrollListener);

        mPresenter.getData(mQuery,Constant.DEFAULT_PAGE);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.searching);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public void onGetMovieSuccess(List<Movie> movies) {
        if (movies.size() != 0 ){
            mTextViewResult.setVisibility(View.INVISIBLE);
        }else{
            mTextViewResult.setVisibility(View.VISIBLE);
        }
        mPresenter.setPageIfLoadSuccess();
        mDisplayMoviesAdapter.replaceData(movies);
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

    @Override
    public String getQuery() {
        return mQuery;
    }

    @Override
    public void requestLoadMore() {
        mPresenter.requestMoreData();
    }

    @Override
    public void onItemClicked(Movie movie) {
        Intent intent = new Intent(this, DetailFilmActivity.class);
        intent.putExtra(Constant.BUNDLE_ID_MOVIE, movie); //Optional parameters
        this.startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        showIndicator();
        mDisplayMoviesAdapter.clearData();
        mPresenter.getData(query,Constant.DEFAULT_PAGE);
        mSearchView.clearFocus();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
