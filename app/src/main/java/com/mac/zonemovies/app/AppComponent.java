package com.mac.zonemovies.app;

import android.content.Context;

import com.mac.zonemovies.data.remote.movieapi.MovieService;
import com.mac.zonemovies.data.remote.movieapi.MoviesModule;
import com.mac.zonemovies.helper.network.NetworkModule;
import com.mac.zonemovies.helper.resources.ResourcesModule;
import com.mac.zonemovies.helper.resources.StringManager;

import dagger.Component;

@Component(modules = {AppModule.class, NetworkModule.class, MoviesModule.class, ResourcesModule.class})
public interface AppComponent {

    Context appContext();
    // enables the injection onto a subcomponent
    MovieService movieAPI();
    StringManager stringManager();

}
