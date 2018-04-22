package com.demo.chapter10;

/**
 * @author make
 * @creare 07/04/2018
 */
public class Outer {

    class Inner {

    }

    public Inner innerClass() {
        return new Inner();
    }

    public static void main(String[] args) {
        Outer o1 = new Outer();
        Outer.Inner i1 = o1.innerClass();

        Outer.Inner i2 = new Outer().new Inner();

    }
}
