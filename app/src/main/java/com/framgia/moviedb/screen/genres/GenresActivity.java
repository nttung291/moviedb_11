package com.framgia.moviedb.screen.genres;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.framgia.moviedb.R;
import com.framgia.moviedb.data.model.Genres;
import com.framgia.moviedb.untils.Utils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import static com.framgia.moviedb.screen.genresdisplaymovie.GenresDisplayMovieActivity.getGenresIntent;

/**
 * Genres Screen.
 */
public class GenresActivity extends AppCompatActivity
        implements GenresContract.View, GenresFilmAdapter.ItemGenresClickListener {

    private GenresContract.Presenter mPresenter;
    private RecyclerView mRecyclerGenres;
    private GenresFilmAdapter mGenresFilmAdapter;
    private AVLoadingIndicatorView mAVLoadingIndicatorView;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres);
        mRecyclerGenres = findViewById(R.id.recycler_genres);
        mAVLoadingIndicatorView = findViewById(R.id.av_loading_genres);
        mToolbar = findViewById(R.id.toolbar_genres);
        initToolbar();
        mPresenter = new GenresPresenter();
        mPresenter.setView(this);
        mGenresFilmAdapter = new GenresFilmAdapter(this, this);
        mRecyclerGenres.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerGenres.setAdapter(mGenresFilmAdapter);
        showIndicator();
        mPresenter.getData();
    }

    public void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.nav_genres);
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
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onStop() {
        mPresenter.onStop();
        super.onStop();
    }

    @Override
    public void onGetGenresSuccess(List<Genres> genres) {
        mGenresFilmAdapter.replaceData(genres);
        hideIndicator();
    }

    @Override
    public void onGetGenresFailure(String message) {
        showIndicator();
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
    public void onItemGenresClicked(Genres genres) {
        startActivity(getGenresIntent(this, genres));
    }
}
