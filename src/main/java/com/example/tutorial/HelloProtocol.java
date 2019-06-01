package com.example.tutorial;

/**
 * @author make
 * @creare 01/12/2018
 */

import org.apache.hadoop.ipc.VersionedProtocol;

import java.io.IOException;

public interface HelloProtocol extends VersionedProtocol {

    public static final long versionID = 1L;

    String echo(String value) throws IOException;

}
