package com.mac.zonemovies.view.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mac.zonemovies.R;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
