package pl.training.groovy.bank.accounts.logger

import groovy.sql.Sql
import groovy.transform.TupleConstructor
import pl.training.groovy.bank.accounts.model.Account
import pl.training.groovy.bank.accounts.Accounts
import pl.training.groovy.bank.accounts.model.Operation

import javax.sql.DataSource

@TupleConstructor
class PostgreTransactionLogger implements Accounts {

    private static final String INSERT_LOG_ENTRY = 'insert into operations (timestamp,type,account_number,funds) values(:timestamp,:type,:account_number,:funds)'

    private Accounts accounts
    private Sql sql

    PostgreTransactionLogger(Accounts accounts, DataSource dataSource) {
        this.accounts = accounts
        sql = new Sql(dataSource)
    }

    Account createAccount() {
        accounts.createAccount()
    }

    void deposit(String accountNumber, Long funds) {
        accounts.deposit(accountNumber, funds)
        insertLogEntry(accountNumber, funds, Operation.DEPOSIT.name())
    }

    void withdraw(String accountNumber, Long funds) {
        accounts.withdraw(accountNumber, funds)
        insertLogEntry(accountNumber, funds, Operation.WITHDRAW.name())
    }

    private void insertLogEntry(String accountNumber, Long funds, String operationType) {
        sql.executeInsert(INSERT_LOG_ENTRY, [timestamp: System.currentTimeMillis(), type: operationType, account_number: accountNumber, funds: funds])
    }

}
