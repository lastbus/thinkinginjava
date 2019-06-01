package com.demo.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author make
 * @creare 19/10/2018
 */

public class HelloWorld {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new MyModule());
        Payment payment = injector.getInstance(Payment.class);

        payment.pay(new BigDecimal(123));


    }
}

class MyModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Payment.class).to(CreditPayment.class);
//        bind(Payment.class).to(BankPayment.class);
    }
}

interface Payment {
    public void pay(BigDecimal bigDecimal);
}

class CreditPayment implements Payment {


    @Override
    public void pay(BigDecimal bigDecimal) {
        System.out.println("use credit card to pay " + bigDecimal.toString());
    }
}

class BankPayment implements Payment {

    @Override
    public void pay(BigDecimal bigDecimal) {
        System.out.println("use bank card to pay " + bigDecimal.toString());

    }
}