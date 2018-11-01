package com.mac.zonemovies.data.remote.movieapi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MovieService {

    private MovieAPI movieAPI;

    @Inject
    public MovieService(MovieAPI movieAPI) {
        this.movieAPI = movieAPI;
    }

    public Observable<List<String>> getMovieList() {
        return movieAPI.getMovies();
    }

}
