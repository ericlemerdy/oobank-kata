package fr.xebia.katas.oobank;

public class BankAccount {
    private BalanceAmount balance = new BalanceAmount();

    public void deposit(DepositAmount i) {
        balance.addAmount(i);
    }

    public BalanceAmount balance() {
        return balance;
    }

    public void withdraw(WithdrawalAmount withdrawalAmount) {
        balance.removeAmount(withdrawalAmount);
    }
}

class BalanceAmount {

    private int amount;

    public void addAmount(DepositAmount depositAmount) {
        this.amount += depositAmount.amount();
    }

    public Integer amout() {
        return amount;
    }

    public void removeAmount(WithdrawalAmount withdrawalAmount) {
        this.amount -= withdrawalAmount.amount();
    }
}

class DepositAmount {
    private final int amount;

    private DepositAmount(int amount) {
        this.amount = amount;
    }

    public static DepositAmount of(int amount) {
        return new DepositAmount(amount);
    }

    public int amount() {
        return this.amount;
    }
}

class WithdrawalAmount {
    private int amount;

    private WithdrawalAmount(int amount) {
        this.amount = amount;
    }

    public static WithdrawalAmount of(int amount) {
        return new WithdrawalAmount(amount);
    }

    public int amount() {
        return this.amount;
    }
}
