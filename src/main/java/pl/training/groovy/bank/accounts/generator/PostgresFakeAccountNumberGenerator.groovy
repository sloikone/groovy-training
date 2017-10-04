package pl.training.groovy.bank.accounts.generator

import groovy.sql.Sql

import javax.sql.DataSource
import java.util.concurrent.atomic.AtomicLong

class PostgresFakeAccountNumberGenerator extends FakeAccountNumberGenerator {

    private static final String SELECT_MAX_ACCOUNT_NUMBER = "select max(number) from accounts"

    PostgresFakeAccountNumberGenerator(DataSource dataSource) {
        new Sql(dataSource).eachRow(SELECT_MAX_ACCOUNT_NUMBER, updateCounter)
    }

    private def updateCounter = {
        String lastAccountNumber = it[0]
        if (lastAccountNumber) {
            counter = new AtomicLong(lastAccountNumber as Long)
        }
    }

}
