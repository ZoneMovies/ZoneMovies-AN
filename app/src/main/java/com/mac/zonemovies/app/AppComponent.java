package com.mac.zonemovies.app;

import android.content.Context;

import com.mac.zonemovies.data.remote.movieapi.MovieAPI;
import com.mac.zonemovies.data.remote.movieapi.MovieService;
import com.mac.zonemovies.data.remote.movieapi.MoviesModule;
import com.mac.zonemovies.util.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, NetworkModule.class, MoviesModule.class})
public interface AppComponent {

    Context appContext();
    MovieService movieAPI();

}
