package org.assignment;

import org.assignment.command.*;
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
        String inputCommand = inputStrings[0];

        Command commandObject = switch (inputCommand) {
            case LOGIN -> {
                String accountName = inputStrings[1];
                if (Objects.isNull(bank.findAccountByName(accountName))) {
                    currentLoggedInAccount = bank.createAccount(accountName);
                } else {
                    currentLoggedInAccount = bank.findAccountByName(accountName);
                }
                yield new Login(currentLoggedInAccount);
            }
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
            case LOGOUT -> {
                Logout logout = new Logout(currentLoggedInAccount);
                this.currentLoggedInAccount = null;
                yield logout;
            }
            default -> null;
        };

        atm.setCurrentCommand(commandObject);
        atm.execute();
    }

    public BankAccount getCurrentLoggedInAccount() {
        return currentLoggedInAccount;
    }
}
