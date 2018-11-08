package com.mac.zonemovies.view.signup;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mac.zonemovies.R;
import com.mac.zonemovies.base.BaseActivity;
import com.mac.zonemovies.base.BaseView;
import com.mac.zonemovies.view.home.HomeActivity;

public class SignUpActivity extends BaseActivity
        implements SignUpContract.View, View.OnClickListener {

    private FirebaseAuth firebaseAuth;

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button signUpButton;

    public static Intent startSignUpActivity(Context context) {
        return new Intent(context, SignUpActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpButton = findViewById(R.id.signupButton);
        signUpButton.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!= null) {
            // go to home
            navigateToHome();
            finish();
        }
    }

    private void createUser(String email, String password) {
        if(!validateForm()) {
            return;
        } else {
            showProgressDialog();
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        navigateToHome();
                    } else {
                        Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            hideProgressDialog();
        }

    }

    private void navigateToHome() {
        startActivity(HomeActivity.startHomeActivity(this));
    }

    private boolean validateForm() {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signupButton:
                createUser(emailEditText.getText().toString(), passwordEditText.getText().toString());
                break;
        }
    }
}
