package pl.training.groovy.bank

import pl.training.groovy.bank.accounts.Account
import pl.training.groovy.bank.accounts.Accounts
import pl.training.groovy.bank.accounts.AccountsService
import pl.training.groovy.bank.accounts.generator.AccountNumberGenerator
import pl.training.groovy.bank.accounts.generator.FakeAccountNumberGenerator
import pl.training.groovy.bank.accounts.logger.ConsoleLogger
import pl.training.groovy.bank.accounts.repository.AccountsRepository
import pl.training.groovy.bank.accounts.repository.HashMapAccountsRepository

import java.text.NumberFormat
import java.util.logging.Level
import java.util.logging.Logger

class App {

    static void main(String[] args) {
        AccountsRepository accountsRepository = new HashMapAccountsRepository()
        AccountNumberGenerator accountNumberGenerator = new FakeAccountNumberGenerator()
        Accounts accountsService = new AccountsService(
                accountsRepository: accountsRepository,
                accountNumberGenerator: accountNumberGenerator)
        NumberFormat formatter = NumberFormat.getCurrencyInstance()
        Accounts accounts = new ConsoleLogger(accounts: accountsService,
                currencyFormatter: { formatter.format(it) })

        //---------------------------------------------
        Logger.getLogger(ConsoleLogger.class.name).setLevel(Level.INFO)

        Account account = accounts.createAccount()
        accounts.deposit(account.number, 100_000_000)
        accounts.withdraw(account.number, 100)
    }

}
