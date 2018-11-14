package com.mac.zonemovies.view.movie;

import com.mac.zonemovies.app.AppComponent;

import dagger.Component;

@MovieScope
@Component(dependencies = {AppComponent.class}, modules = {MovieModule.class})
public interface MovieComponent {

    void inject(MovieDetailActivity movieActivity);

}
