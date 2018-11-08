package com.mac.zonemovies.view.login;

import android.os.Bundle;

import com.mac.zonemovies.R;
import com.mac.zonemovies.base.BaseActivity;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
