package org.assignment;

public class Withdraw implements ATMCommand {
    @Override
    public String execute() {
        return "withdraw";
    }
}
