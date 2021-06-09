package com.example.banker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.banker.Models.AccountModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AccountAdd extends AppCompatActivity {

    EditText bank,balance;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_add);

        bank=findViewById(R.id.accountAddBankEditText);
        balance=findViewById(R.id.accountAddBalanceEditText);
        add=findViewById(R.id.accountAddBtn);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAccount(bank.getText().toString(),balance.getText().toString());
            }
        });

    }

    void addAccount(String bank,String balance)
    {


        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
       FirebaseFirestore db= FirebaseFirestore.getInstance();



        db.collection("users").document(currentUser.getUid()).collection("Accounts").add(new AccountModel(bank,balance))
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {


                        Toast.makeText(AccountAdd.this,"added",Toast.LENGTH_LONG).show();

                       startActivity(new Intent(AccountAdd.this,Home.class));

                    }
                });

    }
}