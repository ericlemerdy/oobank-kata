package fr.xebia.katas.oobank;

public class BankAccount {
    private BalanceAmount balance = new BalanceAmount();

    protected BankAccount() {
    }

    public void deposit(DepositAmount i) {
        balance.addAmount(i);
    }

    public int balance() {
        return balance.amout();
    }

    public void withdraw(WithdrawalAmount withdrawalAmount) {
        balance.removeAmount(withdrawalAmount);
    }

    public TransferableAccount transfert(int amount) {
        if (amount > balance.amout()) {
            throw new TransferCanNotBeGreaterThanBalance();
        }
        return new TransferableAccount(this, amount);
    }
}

class Bank {
    public BankAccount createClient() {
        return new BankAccount();
    }
}

class TransferableAccount {
    private BankAccount origin;
    private int amount;

    public TransferableAccount(BankAccount origin, int amount) {
        this.origin = origin;
        this.amount = amount;
    }

    public void to(BankAccount destination) {
        origin.withdraw(WithdrawalAmount.of(this.amount));
        destination.deposit(DepositAmount.of(this.amount));
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

    private DepositAmount(int amount) throws DepositAmountCanNotBeNegativeOrZero {
        if (amount < 0) {
            throw new DepositAmountCanNotBeNegativeOrZero();
        }
        this.amount = amount;
    }

    public static DepositAmount of(int amount) throws DepositAmountCanNotBeNegativeOrZero {
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

class DepositAmountCanNotBeNegativeOrZero extends RuntimeException {
}

class TransferCanNotBeGreaterThanBalance extends RuntimeException {
}