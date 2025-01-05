package org.assignment;

import org.assignment.model.BankAccount;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ATMClientTest {

    static ATMClient atmClient;
    static ATM atm;
    static Bank bank;

    @BeforeAll
    static void setUp() {
        atm = new ATM();
        bank = new Bank();
        atmClient = new ATMClient(atm, bank);
    }

    @Test
    void shouldProcessLoginCommand() {
        String command = "login Alice";
        assertEquals("Hello, Alice!\nYour balance is $0.0", atmClient.process(command));
    }

    @Test
    void shouldNotCreateAccountIfItExistsAlready() {
        String command1 = "login Alice";
        atmClient.process(command1);

        String command2 = "login Alice";
        atmClient.process(command2);

        assertEquals(1, bank.countAccountsByName("Alice"));
    }

    @Test
    void shouldProcessDepositCommand() {
        atmClient.process("login Alice");

        String command = "deposit 100";
        assertEquals("Your balance is $100.0", atmClient.process(command));

        atmClient.process("withdraw 100");
    }

    @Test
    void shouldProcessWithdrawCommand() {
        atmClient.process("login Alice");
        atmClient.process("deposit 100");

        String command = "withdraw 50";
        assertEquals("Your balance is $50.0", atmClient.process(command));

        atmClient.process("withdraw 50");
    }

    @Test
    void shouldProcessTransferCommand() {
        atmClient.process("login Bob");
        atmClient.process("deposit 50");
        atmClient.process("logout");

        atmClient.process("login Alice");
        atmClient.process("deposit 100");

        atmClient.process("transfer Bob 100");

        BankAccount bobAccount = bank.findAccountByName("Bob");
        assertEquals(150.0d, bobAccount.getBalance());
    }

    @Test
    void shouldProcessLogoutCommand() {
        atmClient.process("login Alice");

        assertEquals("Goodbye, Alice!", atmClient.process("logout"));
    }
}