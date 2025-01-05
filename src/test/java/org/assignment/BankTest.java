package org.assignment;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    static Bank bank;

    @BeforeAll
    static void setUp() {
        bank = new Bank();
    }

    @Test
    void shouldProcessLoginCommand() {
        String command = "login Alice";
        assertEquals("Hello, Alice!", bank.process(command));
    }

    @Test
    void shouldNotCreateAccountIfItExistsAlready() {
        String command1 = "login Alice";
        bank.process(command1);

        String command2 = "login Alice";
        bank.process(command2);

        assertEquals(1, bank.countAccountsByName("Alice"));
    }

    @Test
    void shouldProcessDepositCommand() {
        bank.process("login Alice");

        String command = "deposit 100";
        assertEquals("Your balance is $100.0", bank.process(command));

        bank.process("withdraw 100");
    }

    @Test
    void shouldProcessWithdrawCommand() {
        bank.process("login Alice");
        bank.process("deposit 100");

        String command = "withdraw 50";
        assertEquals("Your balance is $50.0", bank.process(command));

        bank.process("withdraw 50");
    }

    @Test
    void shouldProcessTransferCommand() {
        String command = "transfer Alice 50";
        assertEquals("transfer", bank.process(command));
    }

    @Test
    void shouldProcessLogoutCommand() {
        String command = "logout";
        assertEquals("logout", bank.process(command));
    }
}