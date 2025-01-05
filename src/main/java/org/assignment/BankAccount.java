package org.assignment;

import java.util.Objects;

public class BankAccount {

    private final String name;
    private double balance;

    public BankAccount(String name, double balance) {
        this.name = name;
        this.balance = balance;
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

    public void deposit(Double amount) {
        this.balance += amount;
    }
}
