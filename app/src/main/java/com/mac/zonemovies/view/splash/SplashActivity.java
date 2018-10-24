package com.mac.zonemovies.view.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mac.zonemovies.R;
import com.mac.zonemovies.view.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.LauncherTheme);
        super.onCreate(savedInstanceState);
        // we would validate user session
        // -- has the user been onBoarded
        // we would start background tasks
        // handle navigation
        navigateToRelevantScreen();
    }

    private void navigateToRelevantScreen() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
