package org.assignment.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.assignment.model.OweRecordType.FROM;
import static org.assignment.model.OweRecordType.TO;

public class BankAccount {

    private final String name;
    private double balance;
    private final Map<BankAccount, OweRecord> owingMappings;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankAccount account = (BankAccount) o;

        return Objects.equals(name, account.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public void login() {
        System.out.printf("Hello, %s!%nYour balance is $%s%n", this.name, this.balance);
    }

    public void deposit(Double amount) {
        this.balance += amount;
        System.out.printf("Your balance is $%s%n", this.balance);
    }

    public void withdraw(Double amount) {
        this.balance -= amount;
        System.out.printf("Your balance is $%s%n", this.balance);
    }

    public void logout() {
        System.out.printf("Goodbye, %s!%n", this.name);
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

            this.addOwe(toAccount, toOweRecord);
            toAccount.addOwe(this, fromOweRecord);
        }
    }

    private void addOwe(BankAccount bankAccount, OweRecord oweRecord) {
        this.owingMappings.put(bankAccount, oweRecord);
    }

    public Map<BankAccount, OweRecord> getOwingMappings() {
        return owingMappings;
    }
}
