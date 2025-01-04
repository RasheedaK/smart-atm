package org.assignment;

public class Transfer implements ATMCommand {
    @Override
    public String execute() {
        return "transfer";
    }
}
