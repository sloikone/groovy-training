package pl.training.groovy.bank.accounts

import groovy.transform.TupleConstructor
import pl.training.groovy.bank.accounts.generator.AccountNumberGenerator
import pl.training.groovy.bank.accounts.repository.AccountsRepository

@TupleConstructor
class AccountsService implements Accounts {

    private AccountsRepository accountsRepository
    private AccountNumberGenerator accountNumberGenerator

    Account createAccount() {
        String accountNumber = accountNumberGenerator.next
        Account account = new Account(number: accountNumber, balance: 0)
        accountsRepository.save(account)
    }

    void deposit(String accountNumber, Long funds) {
        Account account = accountsRepository.getByNumber(accountNumber)
        account.deposit(funds)
        accountsRepository.update(account)
    }

    void withdraw(String accountNumber, Long funds) {
        Account account = accountsRepository.getByNumber(accountNumber)
        account.checkFunds(funds)
        account.withdraw(funds)
        accountsRepository.update(account)
    }

}
