package com.demo.nettest;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author make
 * @creare 11/09/2018
 */
public class Abc
{

    public static void main(String[] args) throws UnknownHostException {

        InetAddress address = InetAddress.getByName("localhost2");
//        System.out.println(address.getCanonicalHostName());
        System.out.println(address);
        address = InetAddress.getByName("vipdata.vip.vip.com");
        System.out.println(address);

        address = InetAddress.getByName("10.201.43.217");
        System.out.println(address);
        System.out.println(InetAddress.getLocalHost());

    }
}
