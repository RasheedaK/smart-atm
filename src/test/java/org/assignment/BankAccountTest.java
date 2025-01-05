package org.assignment;

import org.assignment.model.BankAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void shouldReturnTrueIfAccountNamesAreSame() {
        BankAccount account1 = new BankAccount("Alice", 0.0d);
        BankAccount account2 = new BankAccount("Alice", 0.0d);

        assertTrue(account1.equals(account2));
    }

    @Test
    void shouldReturnFalseIfAccountNamesAreDifferent() {
        BankAccount account1 = new BankAccount("Alice", 0.0d);
        BankAccount account2 = new BankAccount("Bob", 0.0d);

        assertFalse(account1.equals(account2));
    }

    @Test
    void shouldDepositAmountToTheAccount() {
        BankAccount account = new BankAccount("Alice", 0.0d);
        account.deposit(100.0d);

        assertEquals(100.0d, account.getBalance());
    }

    @Test
    void shouldWithdrawAmountFromTheAccount() {
        BankAccount account = new BankAccount("Alice", 0.0d);
        account.deposit(50.0d);
        account.withdraw(50.0d);

        assertEquals(0.0d, account.getBalance());
    }
}