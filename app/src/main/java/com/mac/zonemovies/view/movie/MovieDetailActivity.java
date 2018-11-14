package com.mac.zonemovies.view.movie;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.mac.zonemovies.BuildConfig;
import com.mac.zonemovies.R;
import com.mac.zonemovies.app.ZoneMoviesApp;
import com.mac.zonemovies.base.BaseActivity;
import com.mac.zonemovies.data.remote.movieapi.to.credits.Cast;
import com.mac.zonemovies.data.remote.movieapi.to.movie.MovieResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

public class MovieDetailActivity extends BaseActivity implements MovieContract.View, View.OnClickListener {

    private static final String MOVIE_ID_EXTRA = "com.mac.zonemovies.view.movie.MOVIE_ID_EXTRA";
    private static final int REQ_START_STANDALONE_PLAYER = 1;
    private static final int REQ_RESOLVE_SERVICE_MISSING = 2;
    private static final String TAG = "MovieDetailActivityTAG";

    @Inject MoviePresenter moviePresenter;

    private int movieId;

    private Toolbar detailToolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView movieBackdrop;
    private TextView movieOverview;
    private CastAdapter castAdapter;

    public static Intent startMovieDetailActivity(Context context, int movieId) {
        Intent movieIntent = new Intent(context, MovieDetailActivity.class);
        movieIntent.putExtra(MOVIE_ID_EXTRA, movieId);
        return movieIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        initViews();

        setSupportActionBar(detailToolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        DaggerMovieComponent.builder()
                .appComponent(ZoneMoviesApp.getAppComponent())
                .movieModule(new MovieModule(this))
                .build()
                .inject(this);

        movieId = getMovieId(savedInstanceState);

        if (movieId < 0) {
            throw new RuntimeException("Invalid entry intent missing argument MOVIE_ID_EXTRA");
        } else {
            moviePresenter.getMovie(movieId);
            moviePresenter.getMovieCredits(movieId);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(MOVIE_ID_EXTRA, movieId);
    }

    @Override
    public void showMovieDetail(MovieResponse movie) {
        Picasso.get().load(movie.getBackdropURL()).into(movieBackdrop);
        collapsingToolbar.setTitle(movie.getTitle());
        movieOverview.setText(movie.getOverview());
    }

    @Override
    public void showVideo(String videoID) {
        int startTimeMillis = 0;
        Intent intent = YouTubeStandalonePlayer.createVideoIntent(
                this, BuildConfig.YouTubeApiKey, videoID, startTimeMillis, true, false);
        if (intent != null) {
            if (canResolveIntent(intent)) {
                startActivityForResult(intent, REQ_START_STANDALONE_PLAYER);
            } else {
                YouTubeInitializationResult.SERVICE_MISSING.getErrorDialog(this, REQ_RESOLVE_SERVICE_MISSING).show();
            }
        }
    }

    @Override
    public void showMovieCredits(List<Cast> cast) {
        castAdapter.updateDataSet(cast);
        for(Cast actor:cast) {
            Log.d(TAG, "showMovieCredits: path " + actor.getProfilePath());
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                moviePresenter.getMovieVideos(movieId);
                break;
        }
    }

    private void initViews() {
        detailToolbar = findViewById(R.id.toolbar);
        collapsingToolbar = findViewById(R.id.toolbar_layout);
        movieBackdrop = findViewById(R.id.backdropBar);
        movieOverview = findViewById(R.id.movieOverview);
        FloatingActionButton fab = findViewById(R.id.fab);
        RecyclerView movieCast = findViewById(R.id.movieCast);
        movieCast.setHasFixedSize(true);
        castAdapter = new CastAdapter();
        movieCast.setAdapter(castAdapter);
        movieCast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        fab.setOnClickListener(this);
    }

    private int getMovieId(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
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