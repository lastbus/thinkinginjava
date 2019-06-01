package com.demo.chapter18;

import java.io.*;

/**
 * @author make
 * @creare 22/04/2018
 */
public class BufferedInputFile {

    public static String read(String filename) throws IOException {
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
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
