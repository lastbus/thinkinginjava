package com.demo.chapter18;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author make
 * @creare 22/04/2018
 */
public class FormattedMemoryInput {

    public static void main(String[] args) {

        try {
            DataInputStream in = new DataInputStream(
                    new ByteArrayInputStream(
                            BufferedInputFile.read("pom.xml").getBytes()));
            while (true)
                System.out.print((char)in.readByte());

        } catch (IOException e) {
            System.err.println("End of stream");
        }
    }
}
