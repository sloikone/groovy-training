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

    private static def createFormatter = {
        NumberFormat formatter = NumberFormat.getCurrencyInstance()
        formatter.format(it)
    }

    private static def createAccounts = {
        AccountsRepository accountsRepository = new HashMapAccountsRepository()
        AccountNumberGenerator accountNumberGenerator = new FakeAccountNumberGenerator()
        Accounts accountsService = new AccountsService(accountsRepository: accountsRepository, accountNumberGenerator: accountNumberGenerator)
        new ConsoleLogger(accounts: accountsService, currencyFormatter: createFormatter)
    }

    static void main(String[] args) {
        Logger.getLogger(ConsoleLogger.class.name).setLevel(Level.INFO)
        Accounts accounts = createAccounts()
        //---------------------------------------------
        Account account = accounts.createAccount()
        accounts.deposit(account.number, 100_000_000)
        accounts.withdraw(account.number, 100)
    }

}
