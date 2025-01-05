package org.assignment.command;

import org.assignment.BankAccount;

public class Withdraw implements ATMCommand {

    private final BankAccount bankAccount;
    private final Double amount;

    public Withdraw(BankAccount bankAccount, Double amount) {
        this.bankAccount = bankAccount;
        this.amount = amount;
    }

    @Override
    public String execute() {
        this.bankAccount.withdraw(amount);
        return "withdraw";
    }
}
