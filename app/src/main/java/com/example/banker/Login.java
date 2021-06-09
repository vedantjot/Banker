package com.example.banker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText email,password;
    Button login,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.loginEmailEditText);
        password=findViewById(R.id.loginPasswordEditText);

        login=findViewById(R.id.loginLoginBtn);
        signup=findViewById(R.id.loginSignupBtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login(email.getText().toString(),password.getText().toString());
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });



    }

    void login(String email,String password)
    {

        FirebaseAuth mAuth;

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(Login.this, "Successfully Login", Toast.LENGTH_SHORT).show();

                            Intent intent=new Intent(Login.this,Home.class);
                            startActivity(intent);

                        } else {

                            Toast.makeText(Login.this, "Something went wrong, try again", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}