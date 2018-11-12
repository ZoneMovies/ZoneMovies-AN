package com.mac.zonemovies.view.home;

import com.mac.zonemovies.app.AppComponent;

import dagger.Component;

@HomeScope
@Component(dependencies = {AppComponent.class}, modules = {HomeModule.class})
public interface HomeComponent {

    void inject(HomeActivity homeActivity);

}
