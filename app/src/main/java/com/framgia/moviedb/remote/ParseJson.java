package com.framgia.moviedb.remote;

import com.framgia.moviedb.model.Movie;
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
    private static final String KEY_JSON_ID = "id";
    private static final String KEY_JSON_TITLE = "title";
    private static final String KEY_JSON_OVERVIEW = "overview";
    private static final String KEY_JSON_VOTE_AVERAGE = "vote_average";
    private static final String KEY_JSON_POSTER_PATH = "poster_path";
    private static final String KEY_JSON_BACKDROP_PATH = "backdrop_path";
    private static final String KEY_JSON_RELEASE_DATE = "release_date";
    private static final String KEY_JSON_VOTE_COUNT = "vote_count";

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
                    releaseDate,votecount);
            movieList.add(movie);
        }
        return movieList;
    }
}
