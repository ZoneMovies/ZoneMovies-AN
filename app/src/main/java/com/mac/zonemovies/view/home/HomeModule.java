package com.mac.zonemovies.view.home;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

    private final HomeContract.View homeView;


    public HomeModule(HomeContract.View homeView) {
        this.homeView = homeView;
    }

    @HomeScope
    @Provides
    HomeContract.View providesHomeContractView() {
        return homeView;
    }

}
