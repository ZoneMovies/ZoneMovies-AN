package com.mac.zonemovies.data.remote.movieapi;

import com.mac.zonemovies.data.remote.movieapi.to.credits.CreditsResponse;
import com.mac.zonemovies.data.remote.movieapi.to.movie.MovieResponse;
import com.mac.zonemovies.data.remote.movieapi.to.videos.MovieVideosResponse;
import com.mac.zonemovies.data.remote.movieapi.to.showing.NowShowingResponse;

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

    //movie/{movie_id}/videos
    @GET("movie/{movieId}/videos")
    Observable<MovieVideosResponse> getMovieVideos(@Path("movieId") int movieId, @Query("api_key") String API_KEY);

    //movie/{movie_id}/credits
    @GET("movie/{movieId}/credits")
    Observable<CreditsResponse> getMovieCredits(@Path("movieId") int movieId, @Query("api_key") String API_KEY);

}
