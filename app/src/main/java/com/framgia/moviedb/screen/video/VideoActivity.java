package com.framgia.moviedb.screen.video;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.framgia.moviedb.BuildConfig;
import com.framgia.moviedb.R;
import com.framgia.moviedb.data.model.Genres;
import com.framgia.moviedb.data.model.Movie;
import com.framgia.moviedb.screen.EndScrollListener;
import com.framgia.moviedb.screen.LoadMoreListener;
import com.framgia.moviedb.screen.basedisplaymovies.DisplayMoviesAdapter;
import com.framgia.moviedb.untils.Constant;
import com.framgia.moviedb.untils.Utils;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.List;

/**
 * Video Screen.
 */
public class VideoActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener,VideoContract.View,LoadMoreListener
        ,DisplayMoviesAdapter.ItemClickListener {

    private VideoContract.Presenter mPresenter;
    private YouTubePlayerView mYouTubePlayerView;
    private String mKeyVideo;
    private Movie mMovie;
    private Genres mGenres;
    private RecyclerView mRecyclerView;
    private DisplayMoviesAdapter mDisplayMoviesAdapter;
    private EndScrollListener mEndScrollListener;
    private YouTubePlayer.Provider mProvider;
    private YouTubePlayer mYouTubePlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        mRecyclerView = findViewById(R.id.recycler_suggest_video);
        mYouTubePlayerView = findViewById(R.id.youtubeView);

        mMovie = getIntent().getParcelableExtra(Constant.BUNDLE_ID_MOVIE);
        mGenres =  getIntent().getParcelableExtra(Constant.EXTRA_GENRES);

        mDisplayMoviesAdapter = new DisplayMoviesAdapter(this, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, Constant.SPAN_COUNT, GridLayoutManager.VERTICAL, false);
        mEndScrollListener = new EndScrollListener(this);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mDisplayMoviesAdapter);
        mRecyclerView.addOnScrollListener(mEndScrollListener);

        mPresenter = new VideoPresenter(this);
        mPresenter.getData(mMovie.getId());
        mPresenter.getSuggestMovie(Constant.DEFAULT_PAGE,mGenres.getId());
    }

    public void playVideo() {
        if( mYouTubePlayer != null ) {
            mYouTubePlayer.cueVideo(mKeyVideo);
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if(!wasRestored){
            mYouTubePlayer = youTubePlayer;
            playVideo();
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    @Override
    public void onGetMovieSuccess(String key) {
        mKeyVideo = key;
        mYouTubePlayerView.initialize(BuildConfig.API_YOUTUBE_KEY, this);
        playVideo();
    }

    @Override
    public void onGetMoviesFailure(String message) {
        Utils.showMessageGetVideoFailed(this,this);
    }

    @Override
    public void onGetSugesstionSuccess(List<Movie> movies) {
        mDisplayMoviesAdapter.replaceData(movies);
        mEndScrollListener.setLoading(false);
        mPresenter.setPageIfLoadSuccess();
    }

    @Override
    public void onGetSugesstionFailure(String message) {
        Utils.showMessageGetDataFailed(this,this);
    }

    @Override
    public int getID() {
        return mGenres.getId();
    }

    @Override
    public void requestLoadMore() {
        mPresenter.requestMoreData();
    }

    @Override
    public void onItemClicked(Movie movie) {
        mPresenter.getData(movie.getId());
    }

    public static Intent getMovieIntent(Context context, Movie movie,Genres genres) {
        Intent intent = new Intent(context, VideoActivity.class);
        intent.putExtra(Constant.BUNDLE_ID_MOVIE, movie);
        intent.putExtra(Constant.EXTRA_GENRES ,genres);
        return intent;
    }
}
