package com.example.tutorial;

import org.apache.hadoop.ipc.ProtocolSignature;

import java.io.IOException;

/**
 * @author make
 * @creare 01/12/2018
 */
public class HelloProtocolImpl implements HelloProtocol {
    @Override
    public String echo(String value) throws IOException {
        return "Hello, " + value;
    }

    @Override
    public long getProtocolVersion(String protocol, long clientVersion) throws IOException {
        return HelloProtocol.versionID;
    }

    @Override
    public ProtocolSignature getProtocolSignature(String protocol, long clientVersion, int clientMethodsHash) throws IOException {
        return new ProtocolSignature(HelloProtocol.versionID, null);
    }


}
