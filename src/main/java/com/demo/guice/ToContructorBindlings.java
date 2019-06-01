package com.demo.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author make
 * @creare 02/10/2018
 */
public class ToContructorBindlings extends AbstractModule {
    @Override
    protected void configure() {
//        bind(ToCB.class);
        try {
            bind(ToCB.class).toConstructor(ToCB.class.getConstructor(Animal.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new ToContructorBindlings());

        ToCB to = injector.getInstance(ToCB.class);
        to.work();

    }
}


class ToCB {
    private Animal animal;

    public ToCB(Animal animal) {
        this.animal = animal;
    }

    public void work() {
        animal.move();
    }
}
