package com.demo.chapter10.pk2;

import com.demo.chapter10.pk1.Animal;

/**
 * @author make
 * @creare 07/04/2018
 */
public class Outer {

    protected class Fish implements Animal {

        public Fish() {}
        public void move() {
            System.out.println("Fish swims in water");
        }
    }
}
