package org.assignment.model;

import java.util.HashMap;
import java.util.Map;

public class BankAccount {

    private final String name;
    private double balance;
    private final Map<String, OweRecord> owingMappings;

    public BankAccount(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.owingMappings = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(Double amount) {
        this.balance += amount;
    }

    public void withdraw(Double amount) {
        this.balance -= amount;
    }

    public void addOwe(String accountName, OweRecord oweRecord) {
        this.owingMappings.put(accountName, oweRecord);
    }

    public Map<String, OweRecord> getOwingMappings() {
        return owingMappings;
    }
}
