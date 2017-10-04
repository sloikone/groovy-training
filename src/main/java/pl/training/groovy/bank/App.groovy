package pl.training.groovy.bank

import pl.training.groovy.bank.accounts.Accounts
import pl.training.groovy.bank.accounts.factory.AccountsFactory
import pl.training.groovy.bank.accounts.factory.ProductionAccountsFactory
import pl.training.groovy.bank.accounts.factory.TestAccountsFactory
import pl.training.groovy.bank.accounts.logger.ConsoleTransactionLogger
import pl.training.groovy.bank.accounts.model.Account

import java.util.logging.Level
import java.util.logging.Logger

class App {

    static void main(String[] args) {
        Logger.getLogger(ConsoleTransactionLogger.class.name).setLevel(Level.INFO)
        AccountsFactory accountsFactory = new TestAccountsFactory()
        Accounts accounts = accountsFactory.create()
        //---------------------------------------------
        Account account = accounts.createAccount()
        accounts.deposit(account.number, 100_000_000)
        accounts.withdraw(account.number, 100)
    }

}
