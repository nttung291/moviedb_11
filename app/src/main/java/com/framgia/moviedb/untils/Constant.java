package com.framgia.moviedb.untils;

/**
 * Created by TungPC on 03/01/2018.
 */

public final class Constant {

    private Constant() {
        //No-ops
    }

    public static final int VIEWPAGER_NUMBER = 4;
    public static final int DEFAULT_PAGE = 1;
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy";
    public static final String END_POINT_URL = "https://api.themoviedb.org/3/";
    public static final String API_PART = "api_key";
    public static final String LANGUAGE_PART = "language";
    public static final String LANGUAGE = "en-US";
    public static final String PAGE_PART = "page";
    public static final String MOVIE_POPULAR_PART = "movie/popular";
    public static final String MOVIE_NOW_PLAYING_PART = "movie/now_playing";
    public static final String MOVIE_TOP_RATE_PART = "movie/top_rated";
    public static final String MOVIE_UP_COMMING_PART = "movie/upcoming";
    public static final String MOVIES_PART="movies";
    public static final String MOVIE_CREDITS="movie_credits";
    public static final String MOVIE_DETAIL="/movie/";
    public static final String COMPANY_PART="company";
    public static final String ACTOR_PART="person";
    public static final String GENRE_PART="genre";
    public static final String SEARCH_MOVIE_PART="search/movie";
    public static final String QUERY_PART="query";
    public static final String SPACE="%20";
    public static final String IMDB_RATE=" IMDb";
    public static final String IMG_URL = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/";
    public static final int CONNECT_TIME_OUT = 10000;
    public static final int READ_TIME_OUT = 15000;
    public static final String REQUEST_METHOD = "GET";

}
