package com.demo.jettytest;

import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * @author make
 * @creare 24/09/2018
 */
public class TestServletHolder
{

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletHolder sh = new ServletHolder(ServletContainer.class);

        sh.setInitParameter("com.sun.jersey.config.property.resourceConfigClass",
                "com.sun.jersey.api.core.PackagesResourceConfig");
        sh.setInitParameter("com.sun.jersey.config.property.packages",
                "com.demo.jettytest");

        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setContextPath("/api/v1");
        contextHandler.addServlet(sh, "/*");

        server.setHandler(contextHandler);
        server.start();
        server.join();

    }
}
