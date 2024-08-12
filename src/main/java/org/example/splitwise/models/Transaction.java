package org.example.splitwise.models;

public class Transaction {
    private User lender;
    private User borrower;
    private int amount;
    public Transaction(User lender,User borrower,int amount){
        this.lender=lender;
        this.amount=amount;
        this.borrower=borrower;
    }
}
