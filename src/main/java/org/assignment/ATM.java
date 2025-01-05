package org.assignment;

import org.assignment.command.Command;
import org.assignment.command.Deposit;
import org.assignment.command.Transfer;
import org.assignment.command.Withdraw;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ATM {

    public static final String DEPOSIT = "deposit";
    public static final String WITHDRAW = "withdraw";
    public static final String TRANSFER = "transfer";
    public static final String LOGIN = "login";
    public static final String LOGOUT = "logout";

    private final List<BankAccount> bankAccounts = new ArrayList<>();
    private BankAccount currentLoggedInAccount;
    private final Bank bank;

    public ATM(Bank bank) {
        this.bank = bank;
    }

    String process(String input) {
        String[] inputStrings = input.split(" ");
        String command = inputStrings[0];

        return switch (command) {
            case LOGIN -> login(inputStrings[1]);
            case DEPOSIT, WITHDRAW, TRANSFER -> useBankToExecute(inputStrings);
            case LOGOUT -> logout();
            default -> "Invalid command";
        };
    }

    private String useBankToExecute(String[] inputStrings) {
        String inputCommand = inputStrings[0];

        Command commandObject = switch (inputCommand) {
            case DEPOSIT -> {
                Double amount = Double.valueOf(inputStrings[1]);
                yield new Deposit(currentLoggedInAccount, amount);
            }
            case WITHDRAW -> {
                Double amount = Double.valueOf(inputStrings[1]);
                yield new Withdraw(currentLoggedInAccount, amount);
            }
            case TRANSFER -> {
                String toAccountName = inputStrings[1];
                Double amount = Double.valueOf(inputStrings[2]);
                yield new Transfer(currentLoggedInAccount, findAccountByName(toAccountName), amount);
            }
            default -> null;
        };

        bank.setCurrentCommand(commandObject);
        bank.execute();

        return String.format("Your balance is $%s", currentLoggedInAccount.getBalance());
    }

    private String login(String accountName) {
        if (Objects.isNull(findAccountByName(accountName))) {
            currentLoggedInAccount = createAccount(accountName);
        } else {
            currentLoggedInAccount = findAccountByName(accountName);
        }
        return String.format("Hello, %s!", currentLoggedInAccount.getName());
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
