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

    String process(String input) {
        String[] inputStrings = input.split(" ");
        String command = inputStrings[0];

        return switch (command) {
            case LOGIN -> login(inputStrings[1]);
            case DEPOSIT, WITHDRAW, TRANSFER -> useATM(inputStrings);
            case LOGOUT -> logout();
            default -> "Invalid command";
        };
    }

    private String login(String accountName) {
        if (Objects.isNull(bank.findAccountByName(accountName))) {
            currentLoggedInAccount = bank.createAccount(accountName);
        } else {
            currentLoggedInAccount = bank.findAccountByName(accountName);
        }
        return String.format("Hello, %s!%nYour balance is $%s",
                currentLoggedInAccount.getName(), currentLoggedInAccount.getBalance());
    }

    private String logout() {
        String loggedInAccountName = currentLoggedInAccount.getName();
        currentLoggedInAccount = null;
        return String.format("Goodbye, %s!", loggedInAccountName);
    }

    private String useATM(String[] inputStrings) {
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

        return String.format("Your balance is $%s", currentLoggedInAccount.getBalance());
    }
}
