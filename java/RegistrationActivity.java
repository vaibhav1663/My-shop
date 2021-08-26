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

import com.example.myshop.R;
import com.example.myshop.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class RegistrationActivity extends AppCompatActivity {
    Button signup;
    TextView signin;
    TextView name, email, password;
    FirebaseAuth auth;
    FirebaseDatabase database;

    ProgressBar progressBar;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        progressBar =findViewById(R.id.registrationprogressbar);
        progressBar.setVisibility(View.GONE);

        signup = findViewById(R.id.signup);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signin = findViewById(R.id.alreadyacc);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createuser();
                progressBar.setVisibility(View.VISIBLE);

            }
        });

    }
    private void createuser(){
        String username = name.getText().toString();
        String userPassword = password.getText().toString();
        String userEmail = email.getText().toString();
        if (TextUtils.isEmpty(username)){
            Toast.makeText(this, "user name is required", Toast.LENGTH_SHORT).show();
            return;
        }
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

        auth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            UserModel usermodel = new UserModel(username,userEmail,userPassword);
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(usermodel);
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegistrationActivity.this, task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}