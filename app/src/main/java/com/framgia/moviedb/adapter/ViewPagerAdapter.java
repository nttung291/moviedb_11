package com.framgia.moviedb.adapter;

import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.framgia.moviedb.screen.nowplaying.NowPlayingFragment;
import com.framgia.moviedb.screen.popular.PopularFragment;
import com.framgia.moviedb.screen.toprate.TopRateFragment;
import com.framgia.moviedb.screen.upcoming.UpComingFragment;
import java.lang.annotation.Retention;
import static com.framgia.moviedb.untils.Constant.VIEWPAGER_NUMBER;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by Nttung PC on 10/15/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    @Retention(SOURCE)
    @IntDef({FRAGMENT_NOW_PLAYING, FRAGMENT_POPULAR, FRAGMENT_UP_COMING,FRAGMENT_TOP_RATE})
    public @interface FrangmentMode {}
    public static final int FRAGMENT_NOW_PLAYING = 0;
    public static final int FRAGMENT_POPULAR = 1;
    public static final int FRAGMENT_UP_COMING = 2;
    public static final int FRAGMENT_TOP_RATE = 3;
    
    @FrangmentMode int mCurrentFragent = FRAGMENT_NOW_PLAYING;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (mCurrentFragent){
            case FRAGMENT_NOW_PLAYING:
                return new NowPlayingFragment();
            case FRAGMENT_POPULAR:
                return new PopularFragment();
            case FRAGMENT_UP_COMING:
                return new UpComingFragment();
            case FRAGMENT_TOP_RATE :
                return new TopRateFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return VIEWPAGER_NUMBER;
    }

    @FrangmentMode
    public int getCurrentFragment() {
        return mCurrentFragent;
    }

    public void setCurrentFragment(@FrangmentMode int currentFragment) {
        mCurrentFragent = currentFragment;
    }
}
