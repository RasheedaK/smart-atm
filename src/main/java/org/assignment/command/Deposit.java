package org.assignment.command;

public class Deposit implements ATMCommand {
    @Override
    public String execute() {
        return "deposit";
    }
}
