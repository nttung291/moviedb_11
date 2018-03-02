package com.framgia.moviedb.screen.basefragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.moviedb.screen.BasePresenter;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface BaseFragmentContract {
    /**
     * View.
     */
    interface View{

    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getData();
    }
}
