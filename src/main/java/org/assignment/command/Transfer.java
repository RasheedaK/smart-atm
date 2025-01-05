package org.assignment.command;

import org.assignment.model.BankAccount;

public class Transfer implements Command {

    private final BankAccount fromAccount;
    private final BankAccount toAccount;
    private final Double amount;

    public Transfer(BankAccount fromAccount, BankAccount toAccount, Double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public void execute() {
        this.fromAccount.transfer(toAccount, amount);
    }
}
