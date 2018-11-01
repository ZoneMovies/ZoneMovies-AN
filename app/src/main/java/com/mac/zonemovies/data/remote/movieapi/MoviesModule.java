package com.mac.zonemovies.data.remote.movieapi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MoviesModule {

    @Provides
    MovieAPI provideMovieAPI(@Named("movies") Retrofit retrofit) {
        return retrofit.create(MovieAPI.class);
    }

    @Provides
    MovieService provideMovieService(MovieAPI movieAPI){
        return new MovieService(movieAPI);
    }
}
