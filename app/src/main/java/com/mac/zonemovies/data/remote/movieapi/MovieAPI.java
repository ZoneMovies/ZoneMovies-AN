package com.mac.zonemovies.data.remote.movieapi;

import com.mac.zonemovies.data.remote.movieapi.to.MovieResponse;
import com.mac.zonemovies.data.remote.movieapi.to.NowShowingResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPI {

    String BASE_URL = "https://api.themoviedb.org/3/";

    //movie/now_playing?api_key=<<api_key>>&language=en-US&page=1
    @GET("movie/now_playing")
    Observable<NowShowingResponse> getMovies(@Query("api_key") String API_KEY,
                                             @Query("language") String language,
                                             @Query("page") int page);

    //movie/335983?api_key=<<api_key>>
    @GET("movie/{movieId}")
    Observable<MovieResponse> getMovie(@Path("movieId") int movieId, @Query("api_key") String API_KEY);
}
