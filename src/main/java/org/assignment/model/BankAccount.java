package org.assignment.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.assignment.model.OweRecordType.FROM;
import static org.assignment.model.OweRecordType.TO;

public class BankAccount {

    private final String name;
    private double balance;
    private final Map<BankAccount, OweRecord> owingMappings;

    public BankAccount(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.owingMappings = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankAccount account = (BankAccount) o;

        return Objects.equals(name, account.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public void login() {
        System.out.printf("Hello, %s!%nYour balance is $%s%n", this.name, this.balance);
    }

    public void deposit(Double amount) {
        if (this.owingMappings.isEmpty()) {
            this.balance += amount;
            System.out.printf("Your balance is $%s%n", this.balance);
        } else {
            transferByCheckingOweRecords(amount);
        }
    }

    private void transferByCheckingOweRecords(Double amount) {
        for (Map.Entry<BankAccount, OweRecord> entry : owingMappings.entrySet()) {
            if (TO.equals(entry.getValue().type())) {
                this.balance += amount;
                Double amountToTransfer = this.balance >= entry.getValue().amount()
                        ? entry.getValue().amount()
                        : this.balance;

                Double newOweBalance = entry.getValue().amount() - amountToTransfer;

                if (newOweBalance > 0) {
                    owingMappings.put(entry.getKey(), new OweRecord(TO, newOweBalance));
                }
                this.transfer(entry.getKey(), amountToTransfer);
            }
        }
    }

    public void withdraw(Double amount) {
        this.balance -= amount;
        System.out.printf("Your balance is $%s%n", this.balance);
    }

    public void logout() {
        System.out.printf("Goodbye, %s!%n", this.name);
    }

    public void transfer(BankAccount toAccount, Double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            toAccount.balance += amount;

            System.out.printf("Transferred $%s to %s%n", amount, toAccount.name);
        } else {
            toAccount.balance += this.balance;
            Double remainingAmount = amount - this.balance;
            OweRecord toOweRecord = new OweRecord(TO, remainingAmount);
            OweRecord fromOweRecord = new OweRecord(FROM, remainingAmount);
            this.addOwe(toAccount, toOweRecord);
            toAccount.addOwe(this, fromOweRecord);

            System.out.printf("Transferred $%s to %s%n", this.balance, toAccount.name);

            this.balance = 0.0d;
        }

        System.out.printf("Your balance is $%s%n", this.balance);
        if (!this.owingMappings.isEmpty()) {
            for (Map.Entry<BankAccount, OweRecord> entry : owingMappings.entrySet()) {
                System.out.printf("Owed $%s %s %s%n",
                        entry.getValue().amount(),
                        entry.getValue().type().name().toLowerCase(),
                        entry.getKey().getName());
            }
        }
    }

    private void addOwe(BankAccount bankAccount, OweRecord oweRecord) {
        this.owingMappings.put(bankAccount, oweRecord);
    }

    public Map<BankAccount, OweRecord> getOwingMappings() {
        return owingMappings;
    }
}
