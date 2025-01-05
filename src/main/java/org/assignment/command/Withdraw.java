package org.assignment.command;

import org.assignment.model.BankAccount;

public class Withdraw implements Command {

    private final BankAccount bankAccount;
    private final Double amount;

    public Withdraw(BankAccount bankAccount, Double amount) {
        this.bankAccount = bankAccount;
        this.amount = amount;
    }

    @Override
    public void execute() {
        if (this.bankAccount.getBalance() >= amount) {
            this.bankAccount.withdraw(amount);
            System.out.printf("Your balance is $%s%n", this.bankAccount.getBalance());
        } else {
            throw new IllegalArgumentException("Account balance is low, hence cannot withdraw given amount");
        }
    }
}
