package org.assignment.command;

import org.assignment.model.BankAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WithdrawTest {

    @Test
    void shouldThrowExceptionIfBalanceIsLessThanTheAccountToWithdraw() {
        BankAccount account = new BankAccount("Alice", 0.0d);
        account.deposit(50.0d);

        Withdraw withdraw = new Withdraw(account, 100.0d);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, withdraw::execute);

        assertEquals("Account balance is low, hence cannot withdraw given amount", exception.getMessage());
    }

}