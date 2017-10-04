package pl.training.groovy.bank.accounts.logger

import groovy.transform.TupleConstructor
import pl.training.groovy.bank.BankException
import pl.training.groovy.bank.accounts.model.Account
import pl.training.groovy.bank.accounts.Accounts

import java.util.logging.Level
import java.util.logging.Logger

@TupleConstructor
class ConsoleTransactionLogger implements Accounts {

    private Accounts accounts
    private Closure<String> currencyFormatter
    private Logger logger = Logger.getLogger(getClass().name)

    Account createAccount() {
        Account account = accounts.createAccount()
        logger.log(Level.INFO, "New account created ${account.number}")
        account
    }

    void deposit(String accountNumber, Long funds) {
        process {
            accounts.deposit(accountNumber, funds)
            logger.log(Level.INFO, "${accountNumber} <== ${currencyFormatter(funds)}")
        }
    }

    void withdraw(String accountNumber, Long funds) {
        process {
            accounts.withdraw(accountNumber, funds)
            logger.log(Level.INFO, "${accountNumber} ==> ${currencyFormatter(funds)}")
        }
    }

    private void process(Closure<Void> operation) {
        try {
            operation()
            logger.log(Level.INFO, "Status: Success")
        } catch (BankException ex) {
            logger.log(Level.INFO, "Status: Failure ${ex.class.simpleName}")
            throw ex
        }
    }

}
