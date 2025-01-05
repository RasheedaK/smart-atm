package org.assignment;

import org.assignment.model.BankAccount;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        atmClient.process("login Alice");

        assertNotNull(bank.findAccountByName("Alice"));
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
        atmClient.process("deposit 100");

        BankAccount account = bank.findAccountByName("Alice");

        assertEquals(100.0d, account.getBalance());

        atmClient.process("withdraw 100");
    }

    @Test
    void shouldProcessWithdrawCommand() {
        atmClient.process("login Alice");
        atmClient.process("deposit 100");
        atmClient.process("withdraw 50");

        BankAccount account = bank.findAccountByName("Alice");

        assertEquals(50.0d, account.getBalance());

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

        atmClient.process("logout");

        assertNull(atmClient.getCurrentLoggedInAccount());
    }
}