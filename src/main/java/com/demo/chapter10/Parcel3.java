package com.demo.chapter10;

/**
 * @author make
 * @creare 07/04/2018
 */
public class Parcel3 {

    class Contents {
        private int i = 11;

        public int value() {
            return i;
        }
    }

    class Destination {
        private String label;

        public Destination(String whereTo) {
            label = whereTo;
        }

        public String readLabel() {
            return label;
        }
    }

    public static void main(String[] args) {
        Parcel3 p = new Parcel3();
        // Must use instance of the outer class
        // to create an instance of inner class
        Parcel3.Contents c = p.new Contents();
        Parcel3.Destination d = p.new Destination("Tasmania");
    }
}
