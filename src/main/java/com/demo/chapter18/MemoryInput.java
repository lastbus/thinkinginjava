package com.demo.chapter18;

import java.io.IOException;
import java.io.StringReader;

/**
 * @author make
 * @creare 22/04/2018
 */
public class MemoryInput {

    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader(BufferedInputFile.read("pom.xml"));
        int c;
        while ((c = reader.read()) != -1) {
            System.out.print((char) c);
        }
    }
}
