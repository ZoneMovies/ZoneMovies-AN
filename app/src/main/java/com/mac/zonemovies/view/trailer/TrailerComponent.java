package com.mac.zonemovies.view.trailer;

import com.mac.zonemovies.app.AppComponent;

import dagger.Component;

@TrailerScope
@Component(dependencies = {AppComponent.class}, modules = {TrailerModule.class})
public interface TrailerComponent {

    void inject(TrailerActivity trailerActivity);

}
