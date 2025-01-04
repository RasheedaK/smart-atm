package org.assignment;

public class CommandLineParser {

    String process(String input) {
        String[] inputStrings = input.split(" ");
        String command = inputStrings[0];
        return switch (command) {
            case "login" -> new Login().execute();
            case "deposit" -> "deposit";
            case "withdraw" -> "withdraw";
            case "transfer" -> "transfer";
            case "logout" -> "logout";
            default -> "Invalid command";
        };
    }
}
