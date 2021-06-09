package com.example.banker;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banker.Models.AccountModel;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    ArrayList<AccountModel> list;

    public RecyclerAdapter(ArrayList<AccountModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.account_single_row,parent,false);
                return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bank.setText(list.get(position).getBank());
        holder.balance.setText(list.get(position).getBalance());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),AccountEdit.class);
                intent.putExtra("obj",list.get(position));
                v.getContext().startActivity(intent);
            }
        });

        


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView bank,balance;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bank=itemView.findViewById(R.id.accountSingleRowBankText);
            balance=itemView.findViewById(R.id.accountSingleRowBalanceText);
        }
    }
}
