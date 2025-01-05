package org.assignment.model;

import java.util.HashMap;
import java.util.Map;

import static org.assignment.model.OweRecordType.FROM;
import static org.assignment.model.OweRecordType.TO;

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

    public void transfer(BankAccount toAccount, Double amount) {
        if (this.balance >= amount) {
            this.withdraw(amount);
            toAccount.deposit(amount);
        } else {
            toAccount.deposit(amount);
            Double remainingAmount = amount - this.balance;
            OweRecord toOweRecord = new OweRecord(TO, remainingAmount);
            OweRecord fromOweRecord = new OweRecord(FROM, remainingAmount);

            this.addOwe(toAccount.getName(), toOweRecord);
            toAccount.addOwe(this.name, fromOweRecord);
        }
    }

    private void addOwe(String accountName, OweRecord oweRecord) {
        this.owingMappings.put(accountName, oweRecord);
    }

    public Map<String, OweRecord> getOwingMappings() {
        return owingMappings;
    }
}
