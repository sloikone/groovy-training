package pl.training.groovy.bank

import pl.training.groovy.bank.accounts.Account
import pl.training.groovy.bank.accounts.Accounts
import pl.training.groovy.bank.accounts.AccountsService
import pl.training.groovy.bank.accounts.generator.AccountNumberGenerator
import pl.training.groovy.bank.accounts.generator.FakeAccountNumberGenerator
import pl.training.groovy.bank.accounts.repository.AccountsRepository
import pl.training.groovy.bank.accounts.repository.HashMapAccountsRepository

import java.text.NumberFormat

class App {

    static void main(String[] args) {
       NumberFormat formatter = NumberFormat.getCurrencyInstance()
       AccountsRepository accountsRepository = new HashMapAccountsRepository()
       AccountNumberGenerator accountNumberGenerator = new FakeAccountNumberGenerator()
       Accounts accounts = new AccountsService(accountsRepository: accountsRepository, accountNumberGenerator:  accountNumberGenerator,
        currencyFormatter: { formatter.format(it) })
       //---------------------------------------------
       Account account = accounts.createAccount()
       accounts.deposit(account.number, 100_000_000)
       accounts.withdraw(account.number, 100)
       println account
    }

}
