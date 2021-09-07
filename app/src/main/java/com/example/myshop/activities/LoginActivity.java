package com.example.myshop.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myshop.MainActivity;
import com.example.myshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    TextView signup;
    TextView email, password;
    Button signin;

    FirebaseAuth auth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth= FirebaseAuth.getInstance();
        progressBar =findViewById(R.id.loginprogressbar);
        progressBar.setVisibility(View.GONE);

        signup = findViewById(R.id.noacc);
        email = findViewById(R.id.emaillogin);
        password = findViewById(R.id.passwordlogin);
        signin = findViewById(R.id.signin);

        if (auth.getCurrentUser() != null){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
                progressBar.setVisibility(View.VISIBLE);
            }
        });

    }
    private void loginUser(){
        String userPassword = password.getText().toString();
        String userEmail = email.getText().toString();
        if (TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "email is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword)){
            Toast.makeText(this, "password required", Toast.LENGTH_SHORT).show();
            return;
        }
        if(userPassword.length()<8){
            Toast.makeText(this, "Password length should be more than 8", Toast.LENGTH_SHORT).show();
        }
        auth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Signed in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this,task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}