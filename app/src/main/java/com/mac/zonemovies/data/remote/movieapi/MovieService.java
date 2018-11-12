package com.mac.zonemovies.data.remote.movieapi;

import com.mac.zonemovies.BuildConfig;
import com.mac.zonemovies.data.remote.movieapi.to.credits.CreditsResponse;
import com.mac.zonemovies.data.remote.movieapi.to.movie.MovieResponse;
import com.mac.zonemovies.data.remote.movieapi.to.popular.PopularMoviesResponse;
import com.mac.zonemovies.data.remote.movieapi.to.upcoming.UpcomingResponse;
import com.mac.zonemovies.data.remote.movieapi.to.videos.MovieVideosResponse;
import com.mac.zonemovies.data.remote.movieapi.to.showing.NowShowingResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MovieService {

    private static final String API_KEY = BuildConfig.MovieApiKey;
    private static final String DEFAULT_LANGUAGE = "en-US";
    private static final String DEFAULT_REGION = "US";
    private static final int DEFAULT_PAGE = 1;

    private MovieAPI movieAPI;

    @Inject
    public MovieService(MovieAPI movieAPI) {
        this.movieAPI = movieAPI;
    }

    public Observable<NowShowingResponse> getMovieList() {
        return movieAPI.getMovies(
                API_KEY,
                DEFAULT_LANGUAGE,
                DEFAULT_PAGE,
                DEFAULT_REGION
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

    public Observable<PopularMoviesResponse> getPopularMovies() {
        return movieAPI.getPopularMovies(
                API_KEY,
                DEFAULT_LANGUAGE,
                DEFAULT_PAGE,
                DEFAULT_REGION
        );
    }

    public Observable<UpcomingResponse> getUpcomingMovies() {
        return movieAPI.getUpcomingMovies(
                API_KEY,
                DEFAULT_LANGUAGE,
                DEFAULT_PAGE,
                DEFAULT_REGION
        );
    }
}
