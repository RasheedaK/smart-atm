package org.assignment;

public class Logout implements ATMCommand {
    @Override
    public String execute() {
        return "logout";
    }
}
