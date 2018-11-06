package com.mac.zonemovies.view.trailer;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.mac.zonemovies.R;
import com.mac.zonemovies.app.ZoneMoviesApp;
import com.mac.zonemovies.view.movie.MovieActivity;

import javax.inject.Inject;

public class TrailerActivity extends AppCompatActivity implements TrailerContract.View {

    private static final String MOVIE_ID_EXTRA = "com.mac.zonemovies.view.trailer.MOVIE_ID_EXTRA";
    private static final String TAG = "TrailerActivityTAG";

    private int movieId;

    private VideoView videoView;

    @Inject
    TrailerPresenter trailerPresenter;

    public static Intent startTrailerActivity(Context context, int movieId) {
        Intent movieIntent = new Intent(context, TrailerActivity.class);
        movieIntent.putExtra(MOVIE_ID_EXTRA, movieId);
        return movieIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer);
        videoView = findViewById(R.id.videoView);

        movieId = getMovieId(savedInstanceState);

        DaggerTrailerComponent.builder()
                .appComponent(ZoneMoviesApp.getAppComponent())
                .trailerModule(new TrailerModule(this))
                .build()
                .inject(this);

        trailerPresenter.getMovieVideos(movieId);
    }

    @Override
    public void showVideo(String videoUrl) {
        Log.d(TAG, "showVideo: " + videoUrl);
        videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse(videoUrl));
        videoView.start();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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

}
