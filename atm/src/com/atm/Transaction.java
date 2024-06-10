package com.atm;


public class Transaction {
    private static int idCounter = 0; // To keep track of transaction IDs
    private int transactionId;
    private String type; // "Withdrawal", "Transfer Sent", "Transfer Received"
    private int amount;
    private int balanceAfterTransaction;

    public Transaction(String type, int amount, int balanceAfterTransaction) {
        this.transactionId = transactionId; // Increment the counter for each new transaction
        this.type = type;
        this.amount = amount;
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public int getTransactionId() {
        return transactionId;
    }
    
    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public int getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    @Override
    public String toString() {
        return "Transaction ID: " + transactionId + "\n" +
               "Transaction Type: " + type + "\n" +
               "Transaction Amount: " + amount + " ₹\n" +
               "Balance After Transaction: " + balanceAfterTransaction + " ₹";
    }
}