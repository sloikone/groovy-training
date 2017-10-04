package pl.training.groovy.bank.accounts.model

import groovy.transform.Canonical
import pl.training.groovy.bank.accounts.service.InsufficientFundsException

@Canonical
class Account {

    Long id
    String number
    Long balance

    void deposit(Long funds) {
        balance += funds
    }

    void withdraw(Long funds) {
        balance -= funds
    }

    void checkFunds(Long funds) {
        if (balance < funds) {
            throw new InsufficientFundsException()
        }
    }

}
