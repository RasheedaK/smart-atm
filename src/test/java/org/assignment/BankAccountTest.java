package org.assignment;

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
}