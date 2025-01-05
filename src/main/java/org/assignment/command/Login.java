package org.assignment.command;

import org.assignment.model.BankAccount;

public class Login implements Command {

    private final BankAccount bankAccount;

    public Login(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public void execute() {
        this.bankAccount.login();
    }
}

