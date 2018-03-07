package com.framgia.moviedb.screen.basefragment;

import com.framgia.moviedb.untils.Constant;

/**
 * Listens to user actions from the UI ({@link BaseFragment}), retrieves the data and updates
 * the UI as required.
 */
public abstract class BaseFragmentPresenter implements BaseFragmentContract.Presenter {

    protected final BaseFragmentContract.View mView;
    private int mPage;

    public BaseFragmentPresenter(BaseFragmentContract.View view) {
        mView = view;
        mPage = Constant.DEFAULT_PAGE;
    }

    @Override
    public void requestMoreData() {
        mPage++;
        getData(mPage);
    }

    @Override
    public void setPageIfLoadFailed() {
        if (mPage > 1){
            mPage--;
        }
    }
}
