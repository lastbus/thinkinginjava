package com.demo.chapter10;

/**
 * @author make
 * @creare 07/04/2018
 */
public class Exec8 {

    class Inner {

        private int id = 10;

    }

    public void setInnerId(int id) {
        Inner inner = new Inner();
        inner.id = id;
    }

    public static void main(String[] args) {
        Parcel4 p = new Parcel4();
        Destination d = p.destination("");
        Exec8.Inner inner = new Exec8().new Inner();
        int i = inner.id;
    }
}
