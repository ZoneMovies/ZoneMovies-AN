package com.mac.zonemovies.view.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mac.zonemovies.R;
import com.mac.zonemovies.app.ZoneMoviesApp;
import com.mac.zonemovies.base.BaseActivity;
import com.mac.zonemovies.data.remote.movieapi.to.common.Result;
import com.mac.zonemovies.view.movie.MovieDetailActivity;

import java.util.List;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity implements HomeContract.View {

    private static final String TAG = "HomeActivityTAG";

    private HomeMoviesAdapter homeShowingAdapter;
    private HomeMoviesAdapter homePopularAdapter;
    private HomeUpcomingMoviesAdapter homeUpcomingMoviesAdapter;

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

        homePresenter.getNowShowingMovies();
        homePresenter.getPopularMovies();
        homePresenter.getUpcomingMovies();
    }

    @Override
    public void showNowShowingMovies(List<Result> nowShowingMovies) {
        homeShowingAdapter.updateDataSet(nowShowingMovies);
    }

    @Override
    public void showPopularMovies(List<Result> popularMovies) {
        homePopularAdapter.updateDataSet(popularMovies);
        for(Result result:popularMovies) {
            Log.d(TAG, "showPopularMovies: " + result.getTitle());
        }
    }

    @Override
    public void showUpcomingMovies(List<Result> upcomingMovies) {
        homeUpcomingMoviesAdapter.updateDataSet(upcomingMovies);
    }

    @Override
    public void navigateToMovie(int movieId) {
        startActivity(MovieDetailActivity.startMovieDetailActivity(this, movieId));
    }

    private void initViews(){
        RecyclerView showingMoviesRecycler = findViewById(R.id.moviesShowingRecycler);
        homeShowingAdapter = new HomeMoviesAdapter(this);
        showingMoviesRecycler.setAdapter(homeShowingAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        showingMoviesRecycler.setLayoutManager(layoutManager);

        RecyclerView popularMoviesRecycler = findViewById(R.id.popularMoviesRecycler);
        homePopularAdapter = new HomeMoviesAdapter(this);
        popularMoviesRecycler.setAdapter(homePopularAdapter);
        popularMoviesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        RecyclerView upcomingMoviesRecycler = findViewById(R.id.upcomingRecycler);
        homeUpcomingMoviesAdapter = new HomeUpcomingMoviesAdapter(this);
        upcomingMoviesRecycler.setAdapter(homeUpcomingMoviesAdapter);
        upcomingMoviesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }
}
