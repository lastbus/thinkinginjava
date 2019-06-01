package com.demo.guice;

import com.google.inject.Inject;

/**
 * @author make
 * @creare 01/10/2018
 */
public class BillingService {
    private final A processor;
    private final B transactionLog;

    @Inject
    BillingService(A processor,
                   B transactionLog) {
        this.processor = processor;
        this.transactionLog = transactionLog;
    }


}

class A {

}

class B {

}
