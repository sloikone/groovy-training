package pl.training.groovy.bank.accounts.factory

import pl.training.groovy.bank.accounts.Accounts
import pl.training.groovy.bank.accounts.generator.AccountNumberGenerator
import pl.training.groovy.bank.accounts.generator.FakeAccountNumberGenerator
import pl.training.groovy.bank.accounts.logger.ConsoleTransactionLogger
import pl.training.groovy.bank.accounts.repository.AccountsRepository
import pl.training.groovy.bank.accounts.repository.HashMapAccountsRepository
import pl.training.groovy.bank.accounts.service.AccountsService

import java.text.NumberFormat

class TestAccountsFactory implements AccountsFactory {

    private static final Long CURRENCY_UNIT = 100

    private def currencyFormatter = {
        NumberFormat formatter = NumberFormat.getCurrencyInstance()
        formatter.format((it as Double) / CURRENCY_UNIT)
    }
    private AccountsRepository accountsRepository = new HashMapAccountsRepository()
    private AccountNumberGenerator accountNumberGenerator = new FakeAccountNumberGenerator()

    Accounts create() {
        AccountsService accountsService = new AccountsService(accountsRepository: accountsRepository, accountNumberGenerator: accountNumberGenerator)
        accountsService.addObserver { println "Deposit limit on account: ${it.number}" }
        new ConsoleTransactionLogger(accounts: accountsService, currencyFormatter: currencyFormatter)
     }

}
