package com.mac.zonemovies.data.remote.movieapi;

import com.mac.zonemovies.data.remote.movieapi.to.NowShowingResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
}
