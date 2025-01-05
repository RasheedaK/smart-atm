package org.assignment.command;

import org.assignment.model.BankAccount;
import org.assignment.model.OweRecord;
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

        Map<BankAccount, OweRecord> fromAccountOwingMappings = fromAccount.getOwingMappings();
        Map<BankAccount, OweRecord> toAccountOwingMappings = toAccount.getOwingMappings();

        assertTrue(fromAccountOwingMappings.containsKey(toAccount));
        assertTrue(toAccountOwingMappings.containsKey(fromAccount));
    }
}