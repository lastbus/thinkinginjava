package com.example.tutorial;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author make
 * @creare 01/12/2018
 */
public class RPCClient {

    public static void main(String[] args) throws IOException {

        InetSocketAddress addr = new InetSocketAddress("localhost", 10000);

        Configuration conf = new Configuration();

        HelloProtocol proxy = RPC.getProxy(HelloProtocol.class, HelloProtocol.versionID, addr, conf);

        for (int i = 0; i < 1; i++) {
            System.out.println(proxy.echo("make"));
        }
        System.out.println(UserGroupInformation.getCurrentUser());
    }
}
