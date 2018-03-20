package com.framgia.moviedb.screen.favoritefilm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.framgia.moviedb.R;
import com.framgia.moviedb.data.model.Movie;
import com.framgia.moviedb.screen.detailfilm.DetailFilmActivity;
import com.framgia.moviedb.untils.Constant;
import com.framgia.moviedb.untils.Utils;

import java.util.List;

/**
 * FavoriteFilm Screen.
 */
public class FavoriteFilmActivity extends AppCompatActivity implements FavoriteFilmContract.View, FavoriteFilmAdapter.ItemClickListener {

    private FavoriteFilmContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private FavoriteFilmAdapter mFavoriteFilmAdapter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritefilm);
        mRecyclerView = findViewById(R.id.recycler_movies_favorite);
        mToolbar = findViewById(R.id.toolbar_favorite);
        initToolbar();
        mFavoriteFilmAdapter = new FavoriteFilmAdapter(this, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, Constant.SPAN_COUNT, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mFavoriteFilmAdapter);
        mPresenter = new FavoriteFilmPresenter(this, this);
        mPresenter.getMovies();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.favorite);
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
    public void onItemClicked(Movie movie) {
        Intent intent = new Intent(this, DetailFilmActivity.class);
        intent.putExtra(Constant.BUNDLE_ID_MOVIE, movie);
        startActivity(intent);
    }

    @Override
    public void onClickRemove(int positionRemove) {
        mPresenter.removeMovie(positionRemove);
    }

    @Override
    public void onGetMovieSuccess(List<Movie> movies) {
        mFavoriteFilmAdapter.replaceData(movies);
    }

    @Override
    public void onRemoveFavoriteSuccess(List<Movie> movies) {
        mFavoriteFilmAdapter.clearData();
        mFavoriteFilmAdapter.replaceData(movies);
        Utils.showToast(this, this, getResources().getString(R.string.msg_had_delete));
    }

    @Override
    public void onRemoveFavoriteFailed() {
        Utils.showToast(this, this, getResources().getString(R.string.msg_delete_failed));
    }
}
