package com.mac.zonemovies.app;

import android.app.Application;

public class ZoneMoviesApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        // TODO - Setup Logging library -> Timber
        // TODO - Setup Crash reporting
        // TODO - Initialize AppDaggerComponent
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

}
