package com.framgia.moviedb.data.model;

/**
 * Created by nttungg on 03-06-2018.
 */

public class Genres {
    private int mId;
    private String mName;

    public Genres(int id, String name) {
        mId = id;
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
