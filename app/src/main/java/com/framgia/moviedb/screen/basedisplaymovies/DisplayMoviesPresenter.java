package com.framgia.moviedb.screen.basedisplaymovies;

import com.framgia.moviedb.untils.Constant;

/**
 * Listens to user actions from the UI ({@link DisplayMoviesActivity}), retrieves the data and updates
 * the UI as required.
 */
public abstract class DisplayMoviesPresenter implements DisplayMoviesContract.Presenter {
	private int mPage;
	private int mID;
	protected final DisplayMoviesContract.View mView;

	public DisplayMoviesPresenter(DisplayMoviesContract.View view) {
		mView = view;
		mPage = Constant.DEFAULT_PAGE;
	}

	@Override
	public void requestMoreData() {
		mPage++;
		getData(mPage, mView.getID());
	}

	@Override
	public void setPageIfLoadFailed() {
		if (mPage > 1) {
			mPage--;
		}
	}
}
