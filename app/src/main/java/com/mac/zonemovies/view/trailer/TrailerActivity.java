package com.mac.zonemovies.view.trailer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mac.zonemovies.R;

public class TrailerActivity extends AppCompatActivity implements TrailerContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer);
    }
}
