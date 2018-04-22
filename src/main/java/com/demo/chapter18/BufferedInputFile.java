package com.demo.chapter18;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author make
 * @creare 22/04/2018
 */
public class BufferedInputFile {

    public static String read(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = reader.readLine()) != null) {
            sb.append(s);
            sb.append("\n");
        }
        reader.close();
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(read("pom.xml"));
    }
}
