package org.assignment.command;

public class Transfer implements ATMCommand {
    @Override
    public String execute() {
        return "transfer";
    }
}
