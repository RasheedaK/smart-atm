package org.assignment.command;

import org.assignment.model.BankAccount;

public class Deposit implements Command {

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
