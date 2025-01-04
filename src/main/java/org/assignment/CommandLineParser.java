package org.assignment;

import org.assignment.command.*;

public class CommandLineParser {

    String process(String input) {
        String[] inputStrings = input.split(" ");
        String command = inputStrings[0];

        return switch (command) {
            case "login" -> createAccount(inputStrings[1]);
            case "deposit" -> new Deposit().execute();
            case "withdraw" -> new Withdraw().execute();
            case "transfer" -> new Transfer().execute();
            case "logout" -> "logout";
            default -> "Invalid command";
        };
    }

    private String createAccount(String accountName) {
        BankAccount account = new BankAccount(accountName, 0.0d);
        return String.format("Hello, %s!", account.getName());
    }
}
