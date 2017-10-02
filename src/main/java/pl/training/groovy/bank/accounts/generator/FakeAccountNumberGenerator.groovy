package pl.training.groovy.bank.accounts.generator

import java.util.concurrent.atomic.AtomicLong

import static java.lang.String.format as formatString

class FakeAccountNumberGenerator implements AccountNumberGenerator {

    private AtomicLong counter = new AtomicLong()

    String getNext() {
        formatString("%026d", counter.incrementAndGet())
    }

}
