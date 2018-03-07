package com.framgia.moviedb.screen;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by nttungg on 3/6/18.
 */

public class EndScrollListener extends RecyclerView.OnScrollListener{
  private boolean mLoading;
  private LoadMoreListener mLoadMoreListener;

  public EndScrollListener(LoadMoreListener loadMoreListener) {
    mLoadMoreListener = loadMoreListener;
    mLoading = false;
  }

  @Override
  public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    super.onScrolled(recyclerView, dx, dy);
    if (dy <= 0 || mLoadMoreListener == null) {
      return;
    }
    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
    int visibleItemCount = layoutManager.getChildCount();
    int totalItemCount = layoutManager.getItemCount();
    int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();
    if (!isLoading() && (visibleItemCount + pastVisiblesItems) >= totalItemCount) {
       setLoading(true);
       mLoadMoreListener.requestLoadMore();
    }
  }

  public boolean isLoading() {
    return mLoading;
  }

  public void setLoading(boolean loading) {
    mLoading = loading;
  }
}
