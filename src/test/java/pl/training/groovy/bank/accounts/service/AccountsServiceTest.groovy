package pl.training.groovy.bank.accounts.service

import org.junit.Before
import org.junit.Test
import pl.training.groovy.bank.accounts.generator.AccountNumberGenerator
import pl.training.groovy.bank.accounts.model.Account
import pl.training.groovy.bank.accounts.repository.AccountsRepository

import static org.junit.Assert.assertEquals
import static org.mockito.AdditionalAnswers.returnsFirstArg
import static org.mockito.Matchers.any
import static org.mockito.Mockito.*

class AccountsServiceTest {

    private static final String ACCOUNT_NUMBER = '0000000000000000000000001'

    private Account account = new Account(number: ACCOUNT_NUMBER, balance: 0)
    private AccountNumberGenerator numberGenerator
    private AccountsRepository accountsRepository
    private AccountsService accountsService

    @Before
    void init() {
        numberGenerator = mock(AccountNumberGenerator.class)
        when(numberGenerator.next).thenReturn(ACCOUNT_NUMBER)
        accountsRepository = mock(AccountsRepository.class)
        when(accountsRepository.save(any(Account.class))).then(returnsFirstArg())
        accountsService = new AccountsService(accountNumberGenerator: numberGenerator, accountsRepository: accountsRepository)
    }

    @Test
    void shouldSaveCreatedAccount() {
        accountsService.createAccount()
        verify(accountsRepository).save(any(Account.class))
    }

    @Test
    void shouldProperlyInitializeCreatedAccount() {
        assertEquals(account, accountsService.createAccount())
    }

}
