package com.demo.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.ImplementedBy;
import com.google.inject.Injector;

/**
 * @author make
 * @creare 02/10/2018
 */
public class UntargettedBinding  extends AbstractModule {
    @Override
    protected void configure() {
        bind(Animal.class);
        bind(Fish.class);

    }


    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new UntargettedBinding());
        Animal animal = injector.getInstance(Animal.class);

        animal.move();

        Fish fish = injector.getInstance(Fish.class);
        fish.move();

    }

}

@ImplementedBy(Fish.class)
interface Animal {
    public void move();
}

class Fish implements Animal {

    @Override
    public void move() {
        System.out.println("Fish moves in water.");
    }
}
