package org.assignment;

import org.assignment.command.Command;
import org.assignment.command.Deposit;
import org.assignment.command.Transfer;
import org.assignment.command.Withdraw;
import org.assignment.model.BankAccount;

import java.util.Objects;

public class ATMClient {

    private BankAccount currentLoggedInAccount;
    private final ATM atm;
    private final Bank bank;

    public static final String DEPOSIT = "deposit";
    public static final String WITHDRAW = "withdraw";
    public static final String TRANSFER = "transfer";
    public static final String LOGIN = "login";
    public static final String LOGOUT = "logout";

    public ATMClient(ATM atm, Bank bank) {
        this.atm = atm;
        this.bank = bank;
    }

    void process(String input) {
        String[] inputStrings = input.split(" ");
        String command = inputStrings[0];

        switch (command) {
            case LOGIN -> login(inputStrings[1]);
            case DEPOSIT, WITHDRAW, TRANSFER -> useATM(inputStrings);
            case LOGOUT -> logout();
            default -> System.out.printf("Invalid command%n");
        }
    }

    public BankAccount getCurrentLoggedInAccount() {
        return currentLoggedInAccount;
    }

    private void login(String accountName) {
        if (Objects.isNull(bank.findAccountByName(accountName))) {
            currentLoggedInAccount = bank.createAccount(accountName);
        } else {
            currentLoggedInAccount = bank.findAccountByName(accountName);
        }
        System.out.printf("Hello, %s!%nYour balance is $%s%n",
                currentLoggedInAccount.getName(), currentLoggedInAccount.getBalance());
    }

    private void logout() {
        String loggedInAccountName = currentLoggedInAccount.getName();
        currentLoggedInAccount = null;
        System.out.printf("Goodbye, %s!%n", loggedInAccountName);
    }

    private void useATM(String[] inputStrings) {
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
                yield new Transfer(currentLoggedInAccount, bank.findAccountByName(toAccountName), amount);
            }
            default -> null;
        };

        atm.setCurrentCommand(commandObject);
        atm.execute();
    }
}
