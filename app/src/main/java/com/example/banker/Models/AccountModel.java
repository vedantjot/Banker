package com.example.banker.Models;

import java.io.Serializable;

public class AccountModel implements Serializable {
    String bank;
    String balance;
    String ID;

    public AccountModel()
    {

    }

    public AccountModel(String bank, String balance) {
        this.bank = bank;
        this.balance = balance;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
