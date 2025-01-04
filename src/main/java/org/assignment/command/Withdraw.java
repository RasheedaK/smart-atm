package org.assignment.command;

public class Withdraw implements ATMCommand {
    @Override
    public String execute() {
        return "withdraw";
    }
}
