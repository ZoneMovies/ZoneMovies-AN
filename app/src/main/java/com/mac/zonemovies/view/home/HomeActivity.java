package com.mac.zonemovies.view.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mac.zonemovies.R;
import com.mac.zonemovies.app.ZoneMoviesApp;
import com.mac.zonemovies.data.remote.movieapi.to.Result;
import com.mac.zonemovies.view.movie.MovieActivity;

import java.util.List;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    private static final String TAG = "HomeActivityTAG";

    private RecyclerView moviesRecycler;
    private HomeAdapter homeAdapter;

    @Inject
    HomePresenter homePresenter;

    public static Intent startHomeActivity(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();

        DaggerHomeComponent.builder()
                .appComponent(ZoneMoviesApp.getAppComponent())
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);

        homePresenter.getMovies();
    }

    @Override
    public void showMovies(List<Result> results ) {
        homeAdapter.updateDataSet(results);
        for(Result movie:results) {
            Log.d(TAG, "showMovies: " + movie.getPosterPath());
        }
    }

    @Override
    public void navigateToMovie(int movieId) {
        startActivity(MovieActivity.startMovieActivity(this, movieId));
    }

    private void initViews(){
        moviesRecycler = findViewById(R.id.moviesRecycler);
        homeAdapter = new HomeAdapter(this);
        moviesRecycler.setAdapter(homeAdapter);
        moviesRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}
