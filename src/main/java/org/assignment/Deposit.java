package org.assignment;

public class Deposit implements ATMCommand {
    @Override
    public String execute() {
        return "deposit";
    }
}
