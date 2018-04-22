package com.demo.chapter10;

/**
 * @author make
 * @creare 07/04/2018
 */
public class DotNew {

    public class Inner {}

    public static void main(String[] args) {
        DotNew dn = new DotNew();
        DotNew.Inner dni = dn.new Inner();
    }

}
