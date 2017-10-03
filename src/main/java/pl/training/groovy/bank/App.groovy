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

    private def currencyFormatter = {
        NumberFormat formatter = NumberFormat.getCurrencyInstance()
        formatter.format(it)
    }

    private createAccounts() {
        AccountsRepository accountsRepository = new HashMapAccountsRepository()
        AccountNumberGenerator accountNumberGenerator = new FakeAccountNumberGenerator()
        AccountsService accountsService = new AccountsService(accountsRepository: accountsRepository, accountNumberGenerator: accountNumberGenerator)
        DepositObserver observer = new DepositObserver() {

            @Override
            void onBigDeposit(String accountNumber, Long funds) {
                println "Deposit limit on account: ${accountNumber}"
            }

        }
        accountsService.addDepositObserver(observer)
        new ConsoleLogger(accounts: accountsService, currencyFormatter: currencyFormatter)
    }

    static void main(String[] args) {
        App app = new App()
        Logger.getLogger(ConsoleLogger.class.name).setLevel(Level.INFO)
        Accounts accounts = app.createAccounts()
        //---------------------------------------------
        Account account = accounts.createAccount()
        accounts.deposit(account.number, 100_000_000)
        accounts.withdraw(account.number, 100)
    }

}
