package org.assignment;

import org.assignment.command.Command;
import org.assignment.model.BankAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bank {

    private final List<BankAccount> bankAccounts = new ArrayList<>();

    private Command currentCommand;

    public void setCurrentCommand(Command currentCommand) {
        this.currentCommand = currentCommand;
    }

    void execute() {
        this.currentCommand.execute();
    }

    public BankAccount createAccount(String accountName) {
        BankAccount newAccount = new BankAccount(accountName, 0.0d);
        this.bankAccounts.add(newAccount);
        return newAccount;
    }

    public BankAccount findAccountByName(String accountName) {
        Optional<BankAccount> bankAccountOptional = this.bankAccounts
                .stream()
                .filter(bankAccount -> bankAccount.getName().equals(accountName))
                .findFirst();

        return bankAccountOptional.orElse(null);
    }

    // This method is for test
    public long countAccountsByName(String accountName) {
        return this.bankAccounts
                .stream()
                .filter(bankAccount -> bankAccount.getName().equals(accountName))
                .count();
    }
}
