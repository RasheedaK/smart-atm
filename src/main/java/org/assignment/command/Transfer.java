package org.assignment.command;

import org.assignment.BankAccount;
import org.assignment.OweRecord;

import static org.assignment.OweRecordType.FROM;
import static org.assignment.OweRecordType.TO;

public class Transfer implements ATMCommand {

    private final BankAccount fromAccount;
    private final BankAccount toAccount;
    private final Double amount;

    public Transfer(BankAccount fromAccount, BankAccount toAccount, Double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public void execute() {
        if (this.fromAccount.getBalance() >= this.amount) {
            this.fromAccount.withdraw(amount);
            this.toAccount.deposit(this.amount);
        } else {
            this.toAccount.deposit(this.amount);
            Double remainingAmount = this.amount - this.fromAccount.getBalance();
            OweRecord toOweRecord = new OweRecord(TO, remainingAmount);
            OweRecord fromOweRecord = new OweRecord(FROM, remainingAmount);

            this.fromAccount.addOwe(toAccount.getName(), toOweRecord);
            this.toAccount.addOwe(fromAccount.getName(), fromOweRecord);
        }
    }
}
