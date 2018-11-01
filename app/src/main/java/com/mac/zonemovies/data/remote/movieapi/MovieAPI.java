package com.mac.zonemovies.data.remote.movieapi;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MovieAPI {

    String BASE_URL = "https://api.themoviesapi.com/";

    // TODO - Map Movies API's endpoints (retrofit)

    @GET("/")
    Observable<List<String>> getMovies();
}
