package com.demo.guice;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * @author make
 * @creare 01/10/2018
 */
public class BillingService {
    private final A processor;


    @Inject
    BillingService(A processor) {
        this.processor = processor;

    }

    public void hh(){
        processor.p();
//        transactionLog.p();
    }


}

class A {

    public void p(){
        System.out.println("A");
    }

}

@Singleton
class B extends A {

    public B() {
        System.out.println("bbbbb");
    }

    @Override
    public void p() {
        System.out.println("B");
    }

}

class I {

    public void p() {
        System.out.println("iiii");
    };
}

class AProvider extends I implements Provider<I> {

    private String s = null;

    @Inject
    public AProvider(String a) {
        s = a;
    }

    @Override
    public I get() {
        System.out.println("A provider");
        I a = new AProvider("PPPP");
        a.p();
        return a;
    }

    public void p(){
        System.out.println(s);
    }
}