package com.mac.zonemovies.app;

import android.content.Context;

import com.mac.zonemovies.data.remote.movieapi.MovieAPI;
import com.mac.zonemovies.data.remote.movieapi.MovieService;
import com.mac.zonemovies.data.remote.movieapi.MoviesModule;
import com.mac.zonemovies.util.network.NetworkModule;
import com.mac.zonemovies.util.resources.ResourcesModule;
import com.mac.zonemovies.util.resources.StringManager;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, NetworkModule.class, MoviesModule.class, ResourcesModule.class})
public interface AppComponent {

    Context appContext();
    // enables the injection onto a subcomponent
    MovieService movieAPI();
    StringManager stringManager();

}
