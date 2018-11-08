package com.mac.zonemovies.view.movie;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.mac.zonemovies.BuildConfig;
import com.mac.zonemovies.R;
import com.mac.zonemovies.app.ZoneMoviesApp;
import com.mac.zonemovies.base.BaseActivity;
import com.mac.zonemovies.data.remote.movieapi.to.MovieResponse;
import com.mac.zonemovies.view.signup.SignUpActivity;

import java.util.List;

import javax.inject.Inject;

public class MovieActivity extends BaseActivity
        implements MovieContract.View, View.OnClickListener {

    private static final String MOVIE_ID_EXTRA = "com.mac.zonemovies.view.movie.MOVIE_ID_EXTRA";
    private static final int REQ_START_STANDALONE_PLAYER = 1;
    private static final int REQ_RESOLVE_SERVICE_MISSING = 2;
    private static final String TAG = "MovieActivityTAG";

    @Inject
    MoviePresenter moviePresenter;

    private int movieId;
    private TextView movieTitle;
    private Button trailerButton;
    private int startTimeMillis = 0;
    private boolean autoplay = true;
    private boolean lightboxMode = false;
    private Intent intent;


    public static Intent startMovieActivity(Context context, int movieId) {
        Intent movieIntent = new Intent(context, MovieActivity.class);
        movieIntent.putExtra(MOVIE_ID_EXTRA, movieId);
        return movieIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        initViews();

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
    public void showVideo(String videoID) {
        intent = YouTubeStandalonePlayer.createVideoIntent(
                this, BuildConfig.YouTubeApiKey, videoID, startTimeMillis, autoplay, lightboxMode);
        if (intent != null) {
            if (canResolveIntent(intent)) {
                startActivityForResult(intent, REQ_START_STANDALONE_PLAYER);
            } else {
                // Could not resolve the intent - must need to install or update the YouTube API service.
                YouTubeInitializationResult.SERVICE_MISSING
                        .getErrorDialog(this, REQ_RESOLVE_SERVICE_MISSING).show();
            }
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonTrailer:
                moviePresenter.getMovieVideos(movieId);
                break;
        }
    }

    private void initViews() {
        movieTitle = findViewById(R.id.movieTitle);
        trailerButton = findViewById(R.id.buttonTrailer);
        trailerButton.setOnClickListener(this);
    }

    private int getMovieId(Bundle savedInstanceState) {
        if(savedInstanceState == null) {
            return getIntent().getIntExtra(MOVIE_ID_EXTRA, -1);
        } else {
            return savedInstanceState.getInt(MOVIE_ID_EXTRA, -1);
        }
    }

    private boolean canResolveIntent(Intent intent) {
        List<ResolveInfo> resolveInfo = getPackageManager().queryIntentActivities(intent, 0);
        return resolveInfo != null && !resolveInfo.isEmpty();
    }
}
