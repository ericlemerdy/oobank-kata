package fr.xebia.katas.oobank;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class BankAccountTest {

    @Test
    public void should_deposit_amounts() {
        Bank bank = new Bank();
        BankAccount bankAccount = bank.createClient();
        DepositAmount depositAmount;
        depositAmount = DepositAmount.of(100);
        bankAccount.deposit(depositAmount);
        depositAmount = DepositAmount.of(200);
        bankAccount.deposit(depositAmount);

        int balance = bankAccount.balance();

        assertThat(balance).isEqualTo(300);
    }

    @Test(expected = DepositAmountCanNotBeNegativeOrZero.class)
    public void should_not_deposit_negative_amounts() {
        DepositAmount.of(-100);
    }

    @Test
    public void should_withdraw_amounts() {
        Bank bank = new Bank();
        BankAccount bankAccount = bank.createClient();
        WithdrawalAmount depositAmount;
        depositAmount = WithdrawalAmount.of(100);
        bankAccount.withdraw(depositAmount);
        depositAmount = WithdrawalAmount.of(200);
        bankAccount.withdraw(depositAmount);

        int balance = bankAccount.balance();

        assertThat(balance).isEqualTo(-300);
    }

    @Test
    public void should_transfer_to_another_client_in_the_same_bank() throws Exception {
        Bank bank = new Bank();
        BankAccount client1 = bank.createClient();
        DepositAmount depositAmount = DepositAmount.of(100);
        client1.deposit(depositAmount);
        BankAccount client2 = bank.createClient();

        TransferableAccount transferableAccount = client1.transfert(40);
        transferableAccount.to(client2);

        assertThat(client1.balance()).isEqualTo(60);
        assertThat(client2.balance()).isEqualTo(40);
    }

    @Test(expected = TransferCanNotBeGreaterThanBalance.class)
    public void should_not_transfer_if_client_has_not_enough_money() throws Exception {
        Bank bank = new Bank();
        BankAccount client1 = bank.createClient();
        DepositAmount depositAmount = DepositAmount.of(20);
        client1.deposit(depositAmount);

        client1.transfert(30);
    }
}
