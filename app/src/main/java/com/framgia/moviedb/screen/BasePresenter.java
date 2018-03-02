package com.framgia.moviedb.screen;

public interface BasePresenter<T> {

    void setView(T view);

    void onStart();

    void onStop();
}
