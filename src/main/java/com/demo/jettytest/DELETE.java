package com.demo.jettytest;

import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.*;
import java.io.IOException;
import java.util.EnumSet;

/**
 * @author make
 * @creare 25/09/2018
 */
public class DELETE {

    public static void main(String[] args) throws Exception {

        Server server = new Server(8080);

        ServletHolder sh = new ServletHolder(ServletContainer.class);
        sh.setInitParameter("com.sun.jersey.config.property.resourceConfigClass", "com.sun.jersey.api.core.PackagesResourceConfig");
        sh.setInitParameter("com.sun.jersey.config.property.packages", "com.demo.jettytest");
        sh.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true"); // 自动将对象映射成json返回


        ServletContextHandler root = new ServletContextHandler();
        root.addServlet(sh, "/*");
//        root.setContextPath("/api/v1");
        root.addFilter(new FilterHolder(new Filter() {
            @Override
            public void init(FilterConfig filterConfig) throws ServletException {
                System.out.println("filter init");
            }

            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                System.out.println("begin filter");
                System.out.println(request.getLocalName());
                chain.doFilter(request, response);
                System.out.println("finish filter");
            }

            @Override
            public void destroy() {
                System.out.println("filter destroy");

            }
        }), "/*", EnumSet.of(DispatcherType.REQUEST));


        root.addEventListener(new ServletRequestListener() {
            @Override
            public void requestDestroyed(ServletRequestEvent sre) {
                System.out.println("servlet request listener destroy");
            }

            @Override
            public void requestInitialized(ServletRequestEvent sre) {
                System.out.println("servlet request listener init.");
            }
        });
        SessionHandler sessionHandler = new SessionHandler();

        root.setSessionHandler(new SessionHandler());
        server.setHandler(root);
        server.start();
        server.join();
    }
}
