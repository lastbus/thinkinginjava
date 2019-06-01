package com.demo.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * @author make
 * @creare 03/10/2018
 */
public class NoBind extends AbstractModule {

    @Override
    protected void configure() {

    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new NoBind());

        NBA nba = injector.getInstance(NBA.class);

        nba.run();

    }
}

class NBA {

    @Inject
    private NBB nbb;

    public void run() {
        nbb.p();
    }

}

class NBB {
    public void p() {
        System.out.println("NBB print");
    }
}
