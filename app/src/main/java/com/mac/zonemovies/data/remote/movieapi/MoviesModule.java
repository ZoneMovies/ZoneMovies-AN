package com.mac.zonemovies.data.remote.movieapi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MoviesModule {

    @Provides
    MovieAPI provideMovieAPI(Retrofit retrofit) {
        return retrofit.create(MovieAPI.class);
    }

    @Provides
    MovieService provideMovieService(MovieAPI movieAPI){
        return new MovieService(movieAPI);
    }
}
