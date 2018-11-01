package com.mac.zonemovies.view.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mac.zonemovies.R;
import com.mac.zonemovies.app.ZoneMoviesApp;

import java.util.List;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    private static final String TAG = "HomeActivityTAG";

    @Inject
    HomePresenter homePresenter;

    public static Intent startHomeActivity(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DaggerHomeComponent.builder()
                .appComponent(ZoneMoviesApp.getAppComponent())
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);

        homePresenter.getMovies();
    }

    @Override
    public void showMovies(List<String> movies) {
        for(String movie:movies) {
            Log.d(TAG, "showMovies: " + movie);
        }
    }
}
