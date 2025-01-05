package org.assignment;

import org.assignment.command.ATMCommand;

public class Bank {

    private ATMCommand currentCommand;

    public void setCurrentCommand(ATMCommand currentCommand) {
        this.currentCommand = currentCommand;
    }

    void execute() {
        this.currentCommand.execute();
    }

}
