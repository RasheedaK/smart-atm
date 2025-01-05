package org.assignment;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ATMTest {

    static ATM atm;

    @BeforeAll
    static void setUp() {
        atm = new ATM(new Bank());
    }

    @Test
    void shouldProcessLoginCommand() {
        String command = "login Alice";
        assertEquals("Hello, Alice!", atm.process(command));
    }

    @Test
    void shouldNotCreateAccountIfItExistsAlready() {
        String command1 = "login Alice";
        atm.process(command1);

        String command2 = "login Alice";
        atm.process(command2);

        assertEquals(1, atm.countAccountsByName("Alice"));
    }

    @Test
    void shouldProcessDepositCommand() {
        atm.process("login Alice");

        String command = "deposit 100";
        assertEquals("Your balance is $100.0", atm.process(command));

        atm.process("withdraw 100");
    }

    @Test
    void shouldProcessWithdrawCommand() {
        atm.process("login Alice");
        atm.process("deposit 100");

        String command = "withdraw 50";
        assertEquals("Your balance is $50.0", atm.process(command));

        atm.process("withdraw 50");
    }

    @Test
    void shouldProcessTransferCommand() {
        atm.process("login Bob");
        atm.process("deposit 50");
        atm.process("logout");

        atm.process("login Alice");
        atm.process("deposit 100");

        atm.process("transfer Bob 100");

        BankAccount bobAccount = atm.findAccountByName("Bob");
        assertEquals(150.0d, bobAccount.getBalance());
    }

    @Test
    void shouldProcessLogoutCommand() {
        atm.process("login Alice");

        assertEquals("Goodbye, Alice!", atm.process("logout"));
    }
}