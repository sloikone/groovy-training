package pl.training.groovy.bank.accounts.model

import org.junit.Assert;
import org.junit.Test
import pl.training.groovy.bank.accounts.service.InsufficientFundsException

import static org.junit.Assert.*;

class AccountTest {

    private static final Long FUNDS = 1_000
    private static final Long INITIAL_BALANCE = 1_000

    private Account account = new Account([balance: INITIAL_BALANCE])

    @Test
    void shouldIncreaseBalanceAfterMakingDeposit() {
        account.deposit(FUNDS)
        assertEquals(INITIAL_BALANCE + FUNDS, account.balance)
    }

    @Test
    void shouldDecreaseBalanceAfterMakingDeposit() {
        account.withdraw(FUNDS)
        assertEquals(INITIAL_BALANCE - FUNDS, account.balance)
    }

    @Test(expected = InsufficientFundsException.class)
    void shouldThrowExceptionWhenAccountHasInsufficientFunds() {
        account.checkFunds(account.balance + FUNDS)
    }

}
