package org.assignment;

import org.assignment.command.*;

public class CommandLineParser {

    String process(String input) {
        String[] inputStrings = input.split(" ");
        String command = inputStrings[0];
        return switch (command) {
            case "login" -> new Login().execute();
            case "deposit" -> new Deposit().execute();
            case "withdraw" -> new Withdraw().execute();
            case "transfer" -> new Transfer().execute();
            case "logout" -> new Logout().execute();
            default -> "Invalid command";
        };
    }
}
