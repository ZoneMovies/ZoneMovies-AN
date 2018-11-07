package com.mac.zonemovies.data.remote.movieapi;

import com.mac.zonemovies.data.remote.movieapi.to.MovieResponse;
import com.mac.zonemovies.data.remote.movieapi.to.MovieVideosResponse;
import com.mac.zonemovies.data.remote.movieapi.to.NowShowingResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MovieService {

    //TODO - Extract API_KEY to GRADLE Properties
    private static final String API_KEY = "3ea80361244dc8fe95edbe3b6afb4fd2";

    private MovieAPI movieAPI;

    private String defaultLanguage = "en";
    private int defaultPage = 1;

    @Inject
    public MovieService(MovieAPI movieAPI) {
        this.movieAPI = movieAPI;
    }

    public Observable<NowShowingResponse> getMovieList() {
        return movieAPI.getMovies(
                API_KEY,
                defaultLanguage,
                defaultPage
        );
    }

    public Observable<MovieResponse> getMovie(int movieId) {
        return movieAPI.getMovie(movieId, API_KEY);
    }

    public Observable<MovieVideosResponse> getMovieVideos(int movieId) {
        return movieAPI.getMovieVideos(movieId, API_KEY);
    }

}
