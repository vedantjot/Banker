package com.example.banker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {
    EditText email,password;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email=findViewById(R.id.signUpEmailEditText);
        password=findViewById(R.id.signUpPasswordEditText);


        signup=findViewById(R.id.signUpSignupBtn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewUser(email.getText().toString(),password.getText().toString());
            }
        });


    }


   void addNewUser(String email, String password)
    {
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(SignUp.this, "Successfully added", Toast.LENGTH_SHORT).show();
                           // FirebaseUser user = mAuth.getCurrentUser();

                        } else {

                            Toast.makeText(SignUp.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}