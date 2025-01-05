package org.assignment.command;

import org.assignment.BankAccount;

public class Deposit implements ATMCommand {

    private final BankAccount bankAccount;
    private final Double amount;

    public Deposit(BankAccount bankAccount, Double amount) {
        this.bankAccount = bankAccount;
        this.amount = amount;
    }

    @Override
    public void execute() {
        this.bankAccount.deposit(amount);
    }
}
