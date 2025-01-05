package org.assignment.command;

import org.assignment.BankAccount;
import org.assignment.OweRecord;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TransferTest {

    @Test
    void shouldTransferFromAmountToAccount() {
        BankAccount fromAccount = new BankAccount("Alice", 0.0d);
        BankAccount toAccount = new BankAccount("Bob", 0.0d);

        fromAccount.deposit(50.0d);
        Transfer transfer = new Transfer(fromAccount, toAccount, 100.0d);

        transfer.execute();

        Map<String, OweRecord> fromAccountOwingMappings = fromAccount.getOwingMappings();
        Map<String, OweRecord> toAccountOwingMappings = toAccount.getOwingMappings();

        assertTrue(fromAccountOwingMappings.containsKey(toAccount.getName()));
        assertTrue(toAccountOwingMappings.containsKey(fromAccount.getName()));
    }
}