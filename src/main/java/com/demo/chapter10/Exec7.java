package com.demo.chapter10;

/**
 * @author make
 * @creare 07/04/2018
 */
public class Exec7 {

    private int id = 10;

    private int getId() {
        System.out.println("outer id \t" + id);
        return id;
    }


    public void setId(int id) {
        Inner inner = new Inner();
        inner.setId(id);
    }

    class Inner {
        public void setId(int id) {
            Exec7.this.id = id;
            getId();
        }
    }

    public static void main(String[] args) {
        Exec7 e = new Exec7();

        e.getId();

        e.setId(100);

        Exec7.Inner ei = e.new Inner();
        ei.setId(123);
        e.getId();
    }
}
