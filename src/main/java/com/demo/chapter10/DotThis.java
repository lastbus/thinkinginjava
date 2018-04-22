package com.demo.chapter10;

/**
 * @author make
 * @creare 07/04/2018
 */
public class DotThis {

    void f() {
        System.out.println("DotThis.f()");
    }

    public class Inner {
        public DotThis out() {
            return DotThis.this;
            // A plain "this" would be Inner's "this"
        }
    }

    public Inner inner() {
        return new Inner();
    }

    public static void main(String[] args) {
        DotThis dt = new DotThis();
        DotThis.Inner dti = dt.inner();
        dti.out().f();
    }
}
