package org.assignment.command;

public class Login implements ATMCommand {
    @Override
    public String execute() {
        return "login";
    }
}
