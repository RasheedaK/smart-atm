package org.assignment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandLineParserTest {

    @Test
    void shouldProcessLoginCommand() {
        CommandLineParser commandLineParser = new CommandLineParser();

        String command = "login Alice";
        assertEquals("login", commandLineParser.process(command));
    }

    @Test
    void shouldProcessDepositCommand() {
        CommandLineParser commandLineParser = new CommandLineParser();

        String command = "deposit 100";
        assertEquals("deposit", commandLineParser.process(command));
    }

    @Test
    void shouldProcessWithdrawCommand() {
        CommandLineParser commandLineParser = new CommandLineParser();

        String command = "withdraw 50";
        assertEquals("withdraw", commandLineParser.process(command));
    }

    @Test
    void shouldProcessTransferCommand() {
        CommandLineParser commandLineParser = new CommandLineParser();

        String command = "transfer Alice 50";
        assertEquals("transfer", commandLineParser.process(command));
    }

    @Test
    void shouldProcessLogoutCommand() {
        CommandLineParser commandLineParser = new CommandLineParser();

        String command = "logout";
        assertEquals("logout", commandLineParser.process(command));
    }
}