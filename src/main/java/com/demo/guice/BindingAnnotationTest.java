package com.demo.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

/**
 * @author make
 * @creare 14/10/2018
 */
public class BindingAnnotationTest extends AbstractModule {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BindingAnnotationTest());

        ABC a = injector.getInstance(ABC.class);
        a.run();
    }

    @Override
    protected void configure() {
        bind(BindingAnnotationInterface.class)
                .annotatedWith(Names.named("a"))
                .to(BindingAnnotationA.class);
        bind(BindingAnnotationInterface.class)
                .annotatedWith(Names.named("b"))
                .to(BindingAnnotationB.class);
    }
}

class ABC {

    private BindingAnnotationInterface b;

    @Inject
    public ABC(@Named("b") BindingAnnotationInterface  b) {
        this.b = b;
    }

    public void run() {
        b.run();
    }
}


interface BindingAnnotationInterface {
    public void run();
//    public void test();
}

class BindingAnnotationA implements BindingAnnotationInterface{

    @Override
    public void run() {
        System.out.println(this.getClass().getName());
    }
}

class BindingAnnotationB implements BindingAnnotationInterface{

    @Override
    public void run() {
        System.out.println(this.getClass().getName());
    }
}