package com.demo.guice;

import com.demo.App;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;

/**
 * @author make
 * @creare 01/10/2018
 */
public class HH extends AbstractModule
{
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new HH());
        I a = injector.getInstance(I.class);
        a.p();

//        BillingService b = injector.getInstance(BillingService.class);
//        b.hh();
//        b = injector.getInstance(BillingService.class);
//        b.hh();
    }

    @Override
    protected void configure() {
//        bind(A.class).toInstance(new A());
//        bind(A.class).to(B.class);
//        bind(B.class).toInstance(new B());
        bind(I.class).to(AProvider.class);
        bind(String.class).toInstance("AAA");
    }


//    @Provides
    public A createA() {
        return new B();
    }

}
