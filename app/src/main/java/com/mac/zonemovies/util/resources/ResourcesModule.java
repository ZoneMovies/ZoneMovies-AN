package com.mac.zonemovies.util.resources;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ResourcesModule {

    @Provides
    StringManager providesStringManager(Context context) {
        return new StringManager(context);
    }
}
