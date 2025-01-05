package org.assignment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    @Test
    void shouldProcessLoginCommand() {
        Bank bank = new Bank();

        String command = "login Alice";
        assertEquals("Hello, Alice!", bank.process(command));
    }

    @Test
    void shouldNotCreateAccountIfItExistsAlready() {
        Bank bank = new Bank();

        String command1 = "login Alice";
        bank.process(command1);

        String command2 = "login Alice";
        bank.process(command2);

        assertEquals(1, bank.countAccountsByName("Alice"));
    }

    @Test
    void shouldProcessDepositCommand() {
        Bank bank = new Bank();
        bank.process("login Alice");

        String command = "deposit 100";
        assertEquals("Your balance is $100.0", bank.process(command));
    }

    @Test
    void shouldProcessWithdrawCommand() {
        Bank bank = new Bank();
        bank.process("login Alice");
        bank.process("deposit 100");

        String command = "withdraw 50";
        assertEquals("Your balance is $50.0", bank.process(command));
    }

    @Test
    void shouldProcessTransferCommand() {
        Bank bank = new Bank();

        String command = "transfer Alice 50";
        assertEquals("transfer", bank.process(command));
    }

    @Test
    void shouldProcessLogoutCommand() {
        Bank bank = new Bank();

        String command = "logout";
        assertEquals("logout", bank.process(command));
    }
}