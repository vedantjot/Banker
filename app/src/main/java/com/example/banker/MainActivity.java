package com.example.banker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth mAuth;

        mAuth = FirebaseAuth.getInstance();


        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(MainActivity.this,Home.class);
            startActivity(intent);

        }
        else{
            Intent intent = new Intent(MainActivity.this,Login.class);
            startActivity(intent);

        }



    }


}