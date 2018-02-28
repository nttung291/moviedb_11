package com.framgia.moviedb.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.framgia.moviedb.screen.nowplaying.NowPlayingFragment;
import com.framgia.moviedb.screen.popular.PopularFragment;
import com.framgia.moviedb.screen.toprate.TopRateFragment;
import com.framgia.moviedb.screen.upcoming.UpComingFragment;


/**
 * Created by Nttung PC on 10/15/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return  new NowPlayingFragment();
            case 1:
                return new PopularFragment();
            case 2:
                return new UpComingFragment();
            case 3 :
                return new TopRateFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
