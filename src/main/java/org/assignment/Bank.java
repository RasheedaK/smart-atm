package org.assignment;

import org.assignment.command.Deposit;
import org.assignment.command.Transfer;
import org.assignment.command.Withdraw;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Bank {

    private final List<BankAccount> bankAccounts = new ArrayList<>();
    private BankAccount currentLoggedInAccount;

    String process(String input) {
        String[] inputStrings = input.split(" ");
        String command = inputStrings[0];

        return switch (command) {
            case "login" -> login(inputStrings[1]);
            case "deposit" -> deposit(inputStrings);
            case "withdraw" -> withdraw(inputStrings);
            case "transfer" -> transfer(inputStrings);
            case "logout" -> logout();
            default -> "Invalid command";
        };
    }

    private String login(String accountName) {
        if (Objects.isNull(findAccountByName(accountName))) {
            currentLoggedInAccount = createAccount(accountName);
        } else {
            currentLoggedInAccount = findAccountByName(accountName);
        }
        return String.format("Hello, %s!", currentLoggedInAccount.getName());
    }

    private String deposit(String[] inputStrings) {
        Double amount = Double.valueOf(inputStrings[1]);
        new Deposit(currentLoggedInAccount, amount).execute();
        return String.format("Your balance is $%s", currentLoggedInAccount.getBalance());
    }

    private String withdraw(String[] inputStrings) {
        Double amount = Double.valueOf(inputStrings[1]);
        new Withdraw(currentLoggedInAccount, amount).execute();
        return String.format("Your balance is $%s", currentLoggedInAccount.getBalance());
    }

    private String transfer(String[] inputStrings) {
        String toAccount = inputStrings[1];
        Double amount = Double.valueOf(inputStrings[2]);
        new Transfer(currentLoggedInAccount, findAccountByName(toAccount), amount).execute();
        return String.format("Your balance is $%s", currentLoggedInAccount.getBalance());
    }

    private String logout() {
        String loggedInAccountName = currentLoggedInAccount.getName();
        currentLoggedInAccount = null;
        return String.format("Goodbye, %s!", loggedInAccountName);
    }

    private BankAccount createAccount(String accountName) {
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
