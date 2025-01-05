package org.assignment;

import org.assignment.command.*;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private final List<BankAccount> bankAccounts = new ArrayList<>();
    private BankAccount currentLoggedInAccount;

    String process(String input) {
        String[] inputStrings = input.split(" ");
        String command = inputStrings[0];

        return switch (command) {
            case "login" -> createAccount(inputStrings[1]);
            case "deposit" -> deposit(inputStrings);
            case "withdraw" -> new Withdraw().execute();
            case "transfer" -> new Transfer().execute();
            case "logout" -> "logout";
            default -> "Invalid command";
        };
    }

    private String deposit(String[] inputStrings) {
        Double amount = Double.valueOf(inputStrings[1]);
        new Deposit(currentLoggedInAccount, amount).execute();
        return String.format("Your balance is $%s", currentLoggedInAccount.getBalance());
    }

    private String createAccount(String accountName) {
        BankAccount accountToCreate = new BankAccount(accountName, 0.0d);
        if (!bankAccounts.contains(accountToCreate)) {
            currentLoggedInAccount = accountToCreate;
            this.bankAccounts.add(accountToCreate);
        }

        return String.format("Hello, %s!", currentLoggedInAccount.getName());
    }

    // This method is for test
    public long countAccountsByName(String accountName) {
        return this.bankAccounts
                .stream()
                .filter(bankAccount -> bankAccount.getName().equals(accountName))
                .count();
    }
}
