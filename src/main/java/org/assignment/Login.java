package org.assignment;

public class Login implements ATMCommand {
    @Override
    public String execute() {
        return "login";
    }
}
