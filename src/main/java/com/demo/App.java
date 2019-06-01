package com.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println(address.toString());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


    }
}

