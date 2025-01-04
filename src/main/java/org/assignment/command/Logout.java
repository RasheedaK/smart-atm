package org.assignment.command;

public class Logout implements ATMCommand {
    @Override
    public String execute() {
        return "logout";
    }
}
