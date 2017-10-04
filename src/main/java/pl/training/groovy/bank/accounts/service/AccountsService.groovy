package pl.training.groovy.bank.accounts.service

import groovy.transform.TupleConstructor
import pl.training.groovy.bank.accounts.Accounts
import pl.training.groovy.bank.accounts.generator.AccountNumberGenerator
import pl.training.groovy.bank.accounts.model.Account
import pl.training.groovy.bank.accounts.repository.AccountsRepository
import pl.training.groovy.bank.util.Subject

@TupleConstructor
class AccountsService implements Accounts, Subject<Account> {

    private static final Long DEPOSIT_LIMIT = 10_000

    private AccountsRepository accountsRepository
    private AccountNumberGenerator accountNumberGenerator

    Account createAccount() {
        String accountNumber = accountNumberGenerator.next
        Account account = new Account(number: accountNumber, balance: 0)
        accountsRepository.save(account)
    }

    void deposit(String accountNumber, Long funds) {
        process(accountNumber) { Account account ->
            account.deposit(funds)
            checkDepositLimit(account, funds)
        }
    }

    private void checkDepositLimit(Account account, Long deposit) {
        if (deposit >= DEPOSIT_LIMIT) {
            notifyObservers(account)
        }
    }

    void withdraw(String accountNumber, Long funds) {
        process(accountNumber) { account ->
            account.checkFunds(funds)
            account.withdraw(funds)
        }
    }

    private void process(String accountNumber, Closure<Void> operation) {
        Account account = accountsRepository.getByNumber(accountNumber)
        operation(account)
        accountsRepository.update(account)
    }

}
