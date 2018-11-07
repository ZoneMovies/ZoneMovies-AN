package com.mac.zonemovies.view.trailer;

import dagger.Module;
import dagger.Provides;

@Module
public class TrailerModule {

    private final TrailerContract.View trailerView;

    public TrailerModule(TrailerContract.View trailerView) {
        this.trailerView = trailerView;
    }

    @TrailerScope
    @Provides
    TrailerContract.View providesTrailerView() {
        return trailerView;
    }

}
