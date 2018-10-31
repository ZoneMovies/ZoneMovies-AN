package com.mac.zonemovies.view.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mac.zonemovies.R;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    public static Intent startHomeActivity(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

}
