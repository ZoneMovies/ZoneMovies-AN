package com.mac.zonemovies.view.movie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mac.zonemovies.R;

public class MovieActivity extends AppCompatActivity implements MovieContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
    }
}
