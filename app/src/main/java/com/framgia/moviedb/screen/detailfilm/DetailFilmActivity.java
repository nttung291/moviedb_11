package com.framgia.moviedb.screen.detailfilm;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.framgia.moviedb.R;
import com.framgia.moviedb.data.model.Actor;
import com.framgia.moviedb.data.model.Company;
import com.framgia.moviedb.data.model.Genres;
import com.framgia.moviedb.data.model.Movie;
import com.framgia.moviedb.untils.Constant;
import com.framgia.moviedb.untils.Utils;

import java.util.List;

import static com.framgia.moviedb.screen.video.VideoActivity.getMovieIntent;
import static com.framgia.moviedb.screen.actordisplaymovies.ActorDisplayMoviesActivity.getActorIntent;
import static com.framgia.moviedb.screen.companydisplaymovies.CompanyDisplayMoviesActivity.getCompanyIntent;
import static com.framgia.moviedb.screen.genresdisplaymovie.GenresDisplayMovieActivity.getGenresIntent;

/**
 * DetailFilm Screen.
 */
public class DetailFilmActivity extends AppCompatActivity implements DetailFilmContract.View,
        GenresAdapter.ItemGenresClickListener, CompanyAdapter.ItemCompanyClickListener, ActorAdapter.ItemActorClickListener {

    private DetailFilmContract.Presenter mPresenter;
    private Toolbar mToolbar;
    private Movie mMovie;
    private Genres mGenres;
    private TextView mTextViewTitle;
    private TextView mTextViewOverView;
    private ImageView mImageViewBackDrop;
    private GenresAdapter mGenresAdapter;
    private CompanyAdapter mCompanyAdapter;
    private ActorAdapter mActorAdapter;
    private RecyclerView mRecyclerGenres;
    private RecyclerView mRecyclerCompanies;
    private RecyclerView mRecyclerActor;
    private ImageView mImageViewFavorite;
    private FloatingActionButton mFloatingButton;
    private boolean isExisted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailfilm);
        mToolbar = findViewById(R.id.toolbar_filmdetail);
        mTextViewTitle = findViewById(R.id.text_filmtitle_detail);
        mTextViewOverView = findViewById(R.id.text_overview_detail);
        mImageViewBackDrop = findViewById(R.id.image_backdrop_detail);
        mRecyclerGenres = findViewById(R.id.recycler_genres_filmdetail);
        mRecyclerCompanies = findViewById(R.id.recycler_company_filmdetail);
        mRecyclerActor = findViewById(R.id.recycler_actor_detailfilm);
        mImageViewFavorite = findViewById(R.id.image_favorite_detail);
        mFloatingButton = findViewById(R.id.fab);
        mMovie = getIntent().getParcelableExtra(Constant.BUNDLE_ID_MOVIE);
        mPresenter = new DetailFilmPresenter(mMovie, this);
        mPresenter.setView(this);
        setAdapter();
        initUI();
        setOnClick();
    }

    void setOnClick() {
        mImageViewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isExisted) {
                    mPresenter.removeFavorite(mMovie);
                    mImageViewFavorite.setBackgroundResource(R.drawable.ic_favorite_grey);
                    isExisted = false;
                } else {
                    mPresenter.addFavorite(mMovie);
                    mImageViewFavorite.setBackgroundResource(R.drawable.ic_favorite_red);
                    isExisted = true;
                }
            }
        });
        mFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mGenres != null) {
                    startActivity(getMovieIntent(DetailFilmActivity.this, mMovie, mGenres));
                }
            }
        });
    }

    private void setAdapter() {
        mGenresAdapter = new GenresAdapter(this, this);
        mRecyclerGenres.setAdapter(mGenresAdapter);
        mRecyclerGenres.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mCompanyAdapter = new CompanyAdapter(this, this);
        mRecyclerCompanies.setAdapter(mCompanyAdapter);
        mRecyclerCompanies.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mActorAdapter = new ActorAdapter(this, this);
        mRecyclerActor.setAdapter(mActorAdapter);
        mRecyclerActor.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
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

    void initUI() {
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mTextViewTitle.setText(mMovie.getTitle());
        mTextViewOverView.setText(mMovie.getOverview());
        Glide.with(this)
                .load(Constant.IMG_URL + mMovie.getBackdropPath())
                .into(mImageViewBackDrop);
        mPresenter.checkExistFavorite();
        mPresenter.getData();
    }

    @Override
    public void onGetCompanySuccess(List<Company> companies) {
        mCompanyAdapter.replaceData(companies);
    }

    @Override
    public void onGetComanyFailure(String message) {
        Utils.showMessageGetDataFailed(this, this);
    }

    @Override
    public void onGetGenresSuccess(List<Genres> genres) {
        int firstPosition = 0;
        mGenres = genres.get(firstPosition);
        mGenresAdapter.replaceData(genres);
    }

    @Override
    public void onGetGenresFailure(String message) {
        Utils.showMessageGetDataFailed(this, this);
    }

    @Override
    public void onGetActorSuccess(List<Actor> actors) {
        mActorAdapter.replaceData(actors);
    }

    @Override
    public void onGetActorFailure(String message) {
        Utils.showMessageGetDataFailed(this, this);
    }

    @Override
    public void onMovieExistFavorite() {
        isExisted = true;
        mImageViewFavorite.setBackgroundResource(R.drawable.ic_favorite_red);
    }

    @Override
    public void onMovieNotExistFavorite() {
        isExisted = false;
        mImageViewFavorite.setBackgroundResource(R.drawable.ic_favorite_grey);
    }

    @Override
    public void onAddFavoriteSuccess() {
        Utils.showToast(this, this, getResources().getString(R.string.msg_insert_success_to_list_my_favorite));
        mImageViewFavorite.setBackgroundResource(R.drawable.ic_favorite_red);
    }

    @Override
    public void onAddFavoriteFailed() {
        Utils.showToast(this, this, getResources().getString(R.string.error_insert_failed));
    }

    @Override
    public void onRemoveFavoriteSuccess() {
        Utils.showToast(this, this, getResources().getString(R.string.msg_had_delete));
        mImageViewFavorite.setBackgroundResource(R.drawable.ic_favorite_grey);
    }

    @Override
    public void onRemoveFavoriteFailed() {
        Utils.showToast(this, this, getResources().getString(R.string.msg_delete_failed));
    }

    @Override
    public void onItemGenresClicked(Genres genres) {
        startActivity(getGenresIntent(this, genres));
    }

    @Override
    public void onItemCompanyClicked(Company company) {
        startActivity(getCompanyIntent(this, company));
    }

    @Override
    public void onItemActorClicked(Actor actor) {
        startActivity(getActorIntent(this, actor));
    }
}
