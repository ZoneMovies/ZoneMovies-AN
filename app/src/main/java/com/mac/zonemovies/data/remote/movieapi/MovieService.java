package com.mac.zonemovies.data.remote.movieapi;

import com.mac.zonemovies.BuildConfig;
import com.mac.zonemovies.data.remote.movieapi.to.credits.CreditsResponse;
import com.mac.zonemovies.data.remote.movieapi.to.movie.MovieResponse;
import com.mac.zonemovies.data.remote.movieapi.to.videos.MovieVideosResponse;
import com.mac.zonemovies.data.remote.movieapi.to.showing.NowShowingResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MovieService {

    //TODO - Extract API_KEY to GRADLE Properties
    private static final String API_KEY = BuildConfig.MovieApiKey;

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

    public Observable<CreditsResponse> getMovieCredits(int movieId) {
        return movieAPI.getMovieCredits(movieId, API_KEY);
    }

}
