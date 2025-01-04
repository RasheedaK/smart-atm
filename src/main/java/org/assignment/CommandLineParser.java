package org.assignment;

import org.assignment.command.*;

public class CommandLineParser {

    String process(String input) {
        String[] inputStrings = input.split(" ");
        String command = inputStrings[0];
        return switch (command) {
            case "login" -> {
                BankAccount account = new BankAccount(inputStrings[1], 0.0d);
                yield String.format("Hello, %s!", account.getName());
            }
            case "deposit" -> new Deposit().execute();
            case "withdraw" -> new Withdraw().execute();
            case "transfer" -> new Transfer().execute();
            case "logout" -> new Logout().execute();
            default -> "Invalid command";
        };
    }
}
