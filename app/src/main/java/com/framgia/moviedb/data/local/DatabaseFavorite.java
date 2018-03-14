package com.framgia.moviedb.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.framgia.moviedb.data.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nttungg on 3/14/18.
 */

public class DatabaseFavorite extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "movie_db_11";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "movie";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_OVERVIEW = "overview";
    private static final String KEY_VOTE_AVERAGE = "vote_average";
    private static final String KEY_POSTER_PATH = "poster_path";
    private static final String KEY_BACKDROP_PATH = "backdrop_path";
    private static final String KEY_RELEASE_DATE = "release_date";
    private static final String KEY_VOTE_COUNT = "vote_count";
    private Context mContext;

    public DatabaseFavorite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Table_Create="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+
                " ("+KEY_ID+" INTEGER PRIMARY KEY,"+
                KEY_TITLE+" Text,"+
                KEY_OVERVIEW+" Text,"+
                KEY_VOTE_AVERAGE+" Double,"+
                KEY_POSTER_PATH+" Text,"+
                KEY_BACKDROP_PATH+" Text,"+
                KEY_RELEASE_DATE+" Text,"+
                KEY_VOTE_COUNT+" Text)";
        db.execSQL(Table_Create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropFavoriteTable = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(dropFavoriteTable);
        onCreate(db);
    }

    public void addFavorite(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_ID, movie.getId());
            contentValues.put(KEY_TITLE, movie.getTitle());
            contentValues.put(KEY_OVERVIEW, movie.getOverview());
            contentValues.put(KEY_VOTE_AVERAGE, movie.getVoteAverage());
            contentValues.put(KEY_POSTER_PATH, movie.getPosterPath());
            contentValues.put(KEY_BACKDROP_PATH, movie.getBackdropPath());
            contentValues.put(KEY_RELEASE_DATE, movie.getReleaseDate());
            contentValues.put(KEY_VOTE_COUNT, movie.getVoteCount());
            db.insertOrThrow(TABLE_NAME, null, contentValues);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    public List<Movie> getAllFavorite() {
        List<Movie> movieList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Movie movie = new Movie(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                        cursor.getDouble(3), cursor.getString(4), cursor.getString(5),
                        cursor.getString(6),cursor.getString(7));
                movieList.add(movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return movieList;
    }

    public boolean deleteMovieForId(Movie movie) {
        int codeFailed = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        int codeDelete =
                db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] { String.valueOf(movie.getId()) });
        if (codeDelete != codeFailed) {
            return true;
        }
        return false;
    }

    public boolean getExitsFavorite(Movie movie) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, KEY_ID + " = ?",
                new String[] { String.valueOf(movie.getId()) }, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        return false;
    }
}
