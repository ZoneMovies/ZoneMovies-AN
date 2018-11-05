package com.mac.zonemovies.view.movie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.mac.zonemovies.R;
import com.mac.zonemovies.app.ZoneMoviesApp;
import com.mac.zonemovies.data.remote.movieapi.to.MovieResponse;

import javax.inject.Inject;

public class MovieActivity extends AppCompatActivity implements MovieContract.View {

    private static final String MOVIE_ID_EXTRA = "com.mac.zonemovies.view.movie.MOVIE_ID_EXTRA";

    @Inject
    MoviePresenter moviePresenter;

    private int movieId;
    private TextView movieTitle;

    public static Intent startMovieActivity(Context context, int movieId) {
        Intent movieIntent = new Intent(context, MovieActivity.class);
        movieIntent.putExtra(MOVIE_ID_EXTRA, movieId);
        return movieIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        movieTitle = findViewById(R.id.movieTitle);

        // this is the bridge
        DaggerMovieComponent.builder()
                .appComponent(ZoneMoviesApp.getAppComponent())
                .movieModule(new MovieModule(this))
                .build()
                .inject(this);

        movieId = getMovieId(savedInstanceState);

        if(movieId<0) {
           throw new RuntimeException("Invalid entry intent missing argument MOVIE_ID_EXTRA");
        } else {
            moviePresenter.getMovie(movieId);
        }
    }

    private int getMovieId(Bundle savedInstanceState) {
        if(savedInstanceState == null) {
            return getIntent().getIntExtra(MOVIE_ID_EXTRA, -1);
        } else {
            return savedInstanceState.getInt(MOVIE_ID_EXTRA, -1);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(MOVIE_ID_EXTRA, movieId);
    }

    @Override
    public void showMovieDetail(MovieResponse movie) {
        movieTitle.setText(movie.getTitle());
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
