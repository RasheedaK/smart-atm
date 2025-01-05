package org.assignment.command;

import org.assignment.model.BankAccount;

public class Logout implements Command {
    private final BankAccount bankAccount;

    public Logout(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public void execute() {
        this.bankAccount.logout();
    }
}
