package com.demo.jettytest;

import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * @author make
 * @creare 22/09/2018
 */
public class Jetty6 {

    public static void main(String[] args) throws Exception {

        Server server = new Server(8082);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.setContextPath("/api/v1");
//        context.addServlet(new ServletHolder(new HelloServlet()), "/*");
//        context.addServlet(new ServletHolder(new HelloServlet("上学")), "/school");
//        context.addServlet(new ServletHolder(new HelloServlet("小明")), "/person");

        ServletHolder servlet = new ServletHolder(ServletContainer.class);
        // 设置初始化参数
        servlet.setInitParameter("com.sun.jersey.config.property.resourceConfigClass", "com.sun.jersey.api.core.PackagesResourceConfig");
        servlet.setInitParameter("com.sun.jersey.config.property.packages", "com.demo.jettytest");
        servlet.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true"); // 自动将对象映射成json返回

        context.addServlet(servlet, "/*");



        WebAppContext appContext = new WebAppContext();
        appContext.setContextPath("/");
        appContext.setResourceBase("/Users/make/test");

//        FilterHolder filterHolder = context.addFilter("filter", "/context/abc", EnumSet.of(DispatcherType.REQUEST));
//        filterHolder.setInitParameter("aaa", "bbb");

        HandlerList handlers = new HandlerList();
        handlers.addHandler(context);
        handlers.addHandler(appContext);




        server.setHandler(handlers);
        server.start();
        server.join();
    }
}
