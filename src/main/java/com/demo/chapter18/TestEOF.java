package com.demo.chapter18;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author make
 * @creare 22/04/2018
 */
public class TestEOF {

    public static void main(String[] args) throws IOException {

        DataInputStream in = new DataInputStream(
                new ByteArrayInputStream(
                        BufferedInputFile.read("pom.xml").getBytes()));
        while (in.available() != 0)
            System.out.print((char)in.readByte());
    }
}
