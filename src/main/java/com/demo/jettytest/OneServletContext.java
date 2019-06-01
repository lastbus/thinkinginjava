package com.demo.jettytest;

/**
 * @author make
 * @creare 22/09/2018
 */
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OneServletContext
{
    public static void main( String[] args ) throws Exception
    {
        Server server = new Server(8080);

//        ServletContextHandler context = new ServletContextHandler(
//                ServletContextHandler.SESSIONS);
//        context.setContextPath("/");
//        context.setResourceBase(System.getProperty("java.io.tmpdir"));
//        context.setResourceBase("/Users/make/test/");
//        server.setHandler(context);

//        HandlerList handlerList = new HandlerList();
//        handlerList.addHandler();
        // Add dump servlet
//        context.addServlet(HelloWorld.class, "/dump/*");
        // Add default servlet
//        context.addServlet(DefaultServlet.class, "/");

        servletHandler(server);
        server.start();
        server.join();
    }

    public static void servletHandler(Server server)
    {
        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);
        servletHandler.addServletWithMapping(HelloWorldServlet.class, "/*");
    }

    @SuppressWarnings("serial")
    public static class HelloServlet2 extends HttpServlet
    {
        @Override
        protected void doGet( HttpServletRequest request,
                              HttpServletResponse response ) throws ServletException,
                IOException
        {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>Hello from HelloServlet</h1>");
        }
    }

    public static  class HelloWorldServlet extends HttpServlet
    {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException
        {
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_OK);
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            System.out.println(sdf.format(new Date()));
            resp.getWriter().println("{\"time\": \"" + sdf.format(new Date()) + "\" }");
        }


    }
}


