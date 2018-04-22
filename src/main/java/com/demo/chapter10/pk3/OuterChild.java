package com.demo.chapter10.pk3;

import com.demo.chapter10.pk1.Animal;
import com.demo.chapter10.pk2.Outer;

/**
 * @author make
 * @creare 07/04/2018
 */
public class OuterChild extends Outer {

    public Animal fish() {
        Outer o = new Outer();
        return o.new Fish();
//        return null;
    }

    public static void main(String[] args) {
        OuterChild child = new OuterChild();
        child.fish().move();
    }
}
