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

import com.example.banker.Models.AccountModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class AccountEdit extends AppCompatActivity {

    EditText bank,balance;
    Button update,delete;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_edit);

        Intent intent=getIntent();

        AccountModel obj = (AccountModel) intent.getSerializableExtra("obj");

        bank=findViewById(R.id.accountEditBankEditText);
        balance=findViewById(R.id.accountEditBalanceEditText);
        update=findViewById(R.id.accountEditSaveBtn);
        delete=findViewById(R.id.accountEditDeleteBtn);

        bank.setText(obj.getBank());
        balance.setText(obj.getBalance());


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj.setBalance(balance.getText().toString());
                obj.setBank(bank.getText().toString());
                Log.d("update","1");
                updateAccount(obj);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               deleteAccount(obj);
            }
        });





    }

    void deleteAccount(AccountModel obj)
    {
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        db= FirebaseFirestore.getInstance();
        db.collection("users").document(currentUser.getUid()).collection("Accounts").document(obj.getID()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(AccountEdit.this, "Deleted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AccountEdit.this,Home.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AccountEdit.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }




    void updateAccount(AccountModel obj)
    {

        Log.d("update","2");
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        db= FirebaseFirestore.getInstance();
        db.collection("users").document(currentUser.getUid()).collection("Accounts").document(obj.getID()).set(obj).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(AccountEdit.this, "Updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AccountEdit.this,Home.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AccountEdit.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }


}