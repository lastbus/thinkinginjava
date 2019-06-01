package com.example.tutorial;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

/**
 * @author make
 * @creare 01/12/2018
 */
public class RPCServer {

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
//        RPC.setProtocolEngine(conf, HelloProtocol, Protocol);
        RPC.Server server = new RPC.Builder(conf)
                .setProtocol(HelloProtocol.class)
                .setInstance(new HelloProtocolImpl())
                .setBindAddress("localhost")
                .setPort(10000)
                .setNumHandlers(1).build();

        server.start();
    }
}
