package com.framgia.moviedb.screen.actordisplaymovies;

import android.content.Context;
import android.content.Intent;

import com.framgia.moviedb.data.model.Actor;
import com.framgia.moviedb.data.remote.MovieRemoteDataSource;
import com.framgia.moviedb.data.remote.MovieReposity;
import com.framgia.moviedb.screen.basedisplaymovies.DisplayMoviesActivity;
import com.framgia.moviedb.screen.basedisplaymovies.DisplayMoviesContract;
import com.framgia.moviedb.untils.Constant;

/**
 * ActorDisplayMovies Screen.
 */
public class ActorDisplayMoviesActivity extends DisplayMoviesActivity {
    private Actor mActor;

    public static Intent getActorIntent(Context context, Actor actor) {
        Intent intent = new Intent(context, ActorDisplayMoviesActivity.class);
        intent.putExtra(Constant.EXTRA_ACTOR, actor);
        return intent;
    }

    @Override
    protected DisplayMoviesContract.Presenter getPresenter() {
        MovieReposity reposity = new MovieReposity(new MovieRemoteDataSource());
        return new ActorDisplayMoviesPresenter(this, reposity);
    }

    @Override
    public int initID() {
        mActor = getIntent().getParcelableExtra(Constant.EXTRA_ACTOR);
        return mActor.getId();
    }

    @Override
    public String initToolBarTitle() {
        return mActor.getName();
    }
}
