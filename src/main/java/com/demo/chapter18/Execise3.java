package com.demo.chapter18;

import java.io.File;

/**
 * @author make
 * @creare 21/04/2018
 */
public class Execise3 {

    public static void main(String[] args) {
        String path = ".";
        File file = new File(path);
        String[] ditItems;
        if (file.isFile()) {
            System.out.println(file.length());
            System.exit(0);
        }

        ditItems = file.list();
        Long sum = 0L;
        for (String s : ditItems) {
            File f = new File(path + File.separator + s);
            if (f.isFile()) {
                sum += f.length();
            }
        }


        System.out.println(sum);
    }
}
