package com.framgia.moviedb.data.remote;

import com.framgia.moviedb.data.model.Actor;
import com.framgia.moviedb.data.model.Company;
import com.framgia.moviedb.data.model.Genres;
import com.framgia.moviedb.data.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nttungg on 3/2/18.
 */

public class ParseJson {
    private static final String KEY_JSON_OBJECT_RESULT = "results";
    private static final String KEY_JSON_OBJECT_GENRES = "genres";
    private static final String KEY_JSON_OBJECT_CAST = "cast";
    private static final String KEY_JSON_OBJECT_PRODUCTION_COMPANIES = "production_companies";
    private static final String KEY_JSON_ID = "id";
    private static final String KEY_JSON_TITLE = "title";
    private static final String KEY_JSON_OVERVIEW = "overview";
    private static final String KEY_JSON_VOTE_AVERAGE = "vote_average";
    private static final String KEY_JSON_POSTER_PATH = "poster_path";
    private static final String KEY_JSON_BACKDROP_PATH = "backdrop_path";
    private static final String KEY_JSON_RELEASE_DATE = "release_date";
    private static final String KEY_JSON_NAME = "name";
    private static final String KEY_JSON_CHARACTER = "character";
    private static final String KEY_JSON_PROFILE_PATH = "profile_path";
    private static final String KEY_JSON_VOTE_COUNT = "vote_count";
    private static final String KEY_JSON_KEY_YOUTUBE = "key";

    private ParseJson() {
    }

    public static List<Movie> getParseJsonMovie(String s) throws JSONException {
        List<Movie> movieList = new ArrayList<>();
        JSONObject jsonObjectMain = new JSONObject(s);
        JSONArray jsonArrayResult = jsonObjectMain.getJSONArray(KEY_JSON_OBJECT_RESULT);
        for (int i = 0; i < jsonArrayResult.length(); i++) {
            JSONObject jsonObjectResult = jsonArrayResult.getJSONObject(i);
            int id = jsonObjectResult.getInt(KEY_JSON_ID);
            String title = jsonObjectResult.getString(KEY_JSON_TITLE);
            String overview = jsonObjectResult.getString(KEY_JSON_OVERVIEW);
            double voteAverage = jsonObjectResult.getDouble(KEY_JSON_VOTE_AVERAGE);
            String posterPath = jsonObjectResult.getString(KEY_JSON_POSTER_PATH);
            String backdropPath = jsonObjectResult.getString(KEY_JSON_BACKDROP_PATH);
            String releaseDate = jsonObjectResult.getString(KEY_JSON_RELEASE_DATE);
            String votecount = jsonObjectResult.getString(KEY_JSON_VOTE_COUNT);
            Movie movie = new Movie(id, title, overview, voteAverage, posterPath, backdropPath,
                    releaseDate, votecount);
            movieList.add(movie);
        }
        return movieList;
    }

    public static List<Actor> getParseJsonActorMovie(String s) throws JSONException {
        List<Actor> actorList = new ArrayList<>();
        JSONObject jsonObjectMain = new JSONObject(s);
        JSONArray jsonArrayGenres = jsonObjectMain.getJSONArray(KEY_JSON_OBJECT_CAST);
        for (int i = 0; i < jsonArrayGenres.length(); i++) {
            JSONObject jsonObjectGenres = jsonArrayGenres.getJSONObject(i);
            int id = jsonObjectGenres.getInt(KEY_JSON_ID);
            String name = jsonObjectGenres.getString(KEY_JSON_NAME);
            String character = jsonObjectGenres.getString(KEY_JSON_CHARACTER);
            String profilePath = jsonObjectGenres.getString(KEY_JSON_PROFILE_PATH);
            Actor actor = new Actor(id, name, character, profilePath);
            actorList.add(actor);
        }
        return actorList;
    }

    public static List<Genres> getParseJsonGenres(String s) throws JSONException {
        List<Genres> genresList = new ArrayList<>();
        JSONObject jsonObjectMain = new JSONObject(s);
        JSONArray jsonArrayGenres = jsonObjectMain.getJSONArray(KEY_JSON_OBJECT_GENRES);
        for (int i = 0; i < jsonArrayGenres.length(); i++) {
            JSONObject jsonObjectGenres = jsonArrayGenres.getJSONObject(i);
            int id = jsonObjectGenres.getInt(KEY_JSON_ID);
            String name = jsonObjectGenres.getString(KEY_JSON_NAME);
            Genres genres = new Genres(id, name);
            genresList.add(genres);
        }
        return genresList;
    }

    public static List<Company> getParseJsonProductionCompanyOfMovie(String s) throws JSONException {
        List<Company> companyList = new ArrayList<>();
        JSONObject jsonObjectMain = new JSONObject(s);
        JSONArray jsonArrayGenres = jsonObjectMain.getJSONArray(KEY_JSON_OBJECT_PRODUCTION_COMPANIES);
        for (int i = 0; i < jsonArrayGenres.length(); i++) {
            JSONObject jsonObjectGenres = jsonArrayGenres.getJSONObject(i);
            int id = jsonObjectGenres.getInt(KEY_JSON_ID);
            String name = jsonObjectGenres.getString(KEY_JSON_NAME);
            Company company = new Company(id, name);
            companyList.add(company);
        }
        return companyList;
    }

    public static List<Movie> getParseJsonMovieForActor(String s) throws JSONException {
        List<Movie> movieList = new ArrayList<>();
        JSONObject jsonObjectMain = new JSONObject(s);
        JSONArray jsonArrayGenres = jsonObjectMain.getJSONArray(KEY_JSON_OBJECT_CAST);
        for (int i = 0; i < jsonArrayGenres.length(); i++) {
            JSONObject jsonObjectGenres = jsonArrayGenres.getJSONObject(i);
            int id = jsonObjectGenres.getInt(KEY_JSON_ID);
            String title = jsonObjectGenres.getString(KEY_JSON_TITLE);
            String overview = jsonObjectGenres.getString(KEY_JSON_OVERVIEW);
            double voteAverage = jsonObjectGenres.getDouble(KEY_JSON_VOTE_AVERAGE);
            String posterPath = jsonObjectGenres.getString(KEY_JSON_POSTER_PATH);
            String backdropPath = jsonObjectGenres.getString(KEY_JSON_BACKDROP_PATH);
            String releaseDate = jsonObjectGenres.getString(KEY_JSON_RELEASE_DATE);
            String votecount = jsonObjectGenres.getString(KEY_JSON_VOTE_COUNT);
            Movie movie = new Movie(id, title, overview, voteAverage, posterPath, backdropPath,
                    releaseDate, votecount);
            movieList.add(movie);
        }
        return movieList;
    }

    public static String getParseJsonKeyYoutube(String s) throws JSONException {
        JSONObject jsonObjectMain = new JSONObject(s);
        JSONArray jsonArrayResult = jsonObjectMain.getJSONArray(KEY_JSON_OBJECT_RESULT);
        int position = 1;
        JSONObject jsonObjectResult = jsonArrayResult.getJSONObject(position);
        String key = jsonObjectResult.getString(KEY_JSON_KEY_YOUTUBE);
        return key;
    }
}
