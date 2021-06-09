package com.example.banker.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.banker.AccountAdd;
import com.example.banker.Models.AccountModel;
import com.example.banker.R;
import com.example.banker.RecyclerAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    FloatingActionButton FAB;

   private RecyclerView recyclerView;

    RecyclerAdapter recyclerAdapter;

    ArrayList<AccountModel> accountList;
    FirebaseFirestore db;

    ProgressBar progressBar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        progressBar.setVisibility(View.VISIBLE);
        FAB=view.findViewById(R.id.fragmentAccountFloatingButton);

        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(getActivity(), AccountAdd.class);
                startActivity(intent);

            }
        });


        recyclerView = view.findViewById(R.id.fragAccountRec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        // data filling

        FirebaseAuth mAuth;

        mAuth = FirebaseAuth.getInstance();


        FirebaseUser currentUser = mAuth.getCurrentUser();




        accountList=new ArrayList<>();


        recyclerAdapter = new RecyclerAdapter(accountList);
        recyclerView.setAdapter(recyclerAdapter);


                db=FirebaseFirestore.getInstance();
        db.collection("users").document(currentUser.getUid()).collection("Accounts").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                        for(DocumentSnapshot d: list)
                        {

                            AccountModel obj =d.toObject(AccountModel.class);
                            obj.setID(d.getId());
                            accountList.add(obj);

                            Log.d("msg",obj.getID());
                        }
                        progressBar.setVisibility(View.INVISIBLE);
                        recyclerAdapter.notifyDataSetChanged();
                    }
                });




        return view;
    }


}