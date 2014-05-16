package fr.xebia.katas.oobank;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class BankAccountTest {

    @Test
    public void should_deposit_amounts() throws Exception {
        BankAccount bankAccount = new BankAccount();
        DepositAmount depositAmount;
        depositAmount = DepositAmount.of(100);
        bankAccount.deposit(depositAmount);
        depositAmount = DepositAmount.of(200);
        bankAccount.deposit(depositAmount);

        BalanceAmount balance = bankAccount.balance();

        assertThat(balance.amout()).isEqualTo(300);
    }

    @Test
    public void should_withdraw_amounts() throws Exception {
        BankAccount bankAccount = new BankAccount();
        WithdrawalAmount depositAmount;
        depositAmount = WithdrawalAmount.of(100);
        bankAccount.withdraw(depositAmount);
        depositAmount = WithdrawalAmount.of(200);
        bankAccount.withdraw(depositAmount);

        BalanceAmount balance = bankAccount.balance();

        assertThat(balance.amout()).isEqualTo(-300);
    }
}
