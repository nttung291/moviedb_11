package com.framgia.moviedb.data.model;

/**
 * Created by nttungg on 03-06-2018.
 */

public class Actor {
    private int mId;
    private String mName;
    private String mCharacter;
    private String mProfilePath;

    public Actor(int id, String name, String character, String profilePath) {
        mId = id;
        mName = name;
        mCharacter = character;
        mProfilePath = profilePath;
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

    public String getCharacter() {
        return mCharacter;
    }

    public void setCharacter(String character) {
        mCharacter = character;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

    public void setProfilePath(String profilePath) {
        mProfilePath = profilePath;
    }
}
