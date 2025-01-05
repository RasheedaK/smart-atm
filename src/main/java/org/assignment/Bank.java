package org.assignment;

import org.assignment.command.Command;

public class Bank {

    private Command currentCommand;

    public void setCurrentCommand(Command currentCommand) {
        this.currentCommand = currentCommand;
    }

    void execute() {
        this.currentCommand.execute();
    }

}
