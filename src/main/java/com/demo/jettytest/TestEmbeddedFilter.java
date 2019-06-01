package com.demo.jettytest;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Enumeration;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * @author make
 * @creare 22/09/2018
 */


public class TestEmbeddedFilter {

    public static void main(String[] args) {
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        ServletHandler handler = new ServletHandler();
        ServletHolder sh = new ServletHolder(new MyServlet());

        context.addServlet(sh, "/*");

        FilterHolder fh = handler.addFilterWithMapping(MyFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        fh.setInitParameter("initParamKey", "InitParamValue");

//        context.addFilter(fh, "/*", EnumSet.of(DispatcherType.REQUEST));
        context.addFilter(new FilterHolder(new MyFilter()), "/*", EnumSet.of(DispatcherType.REQUEST));

//        context.setHandler(handler);

        server.setHandler(context);
        try {
            server.start();
            // server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("serial")
    public static class MyServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException,
                IOException {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>Hello MyServlet</h1>");

        }
    }

    public static class MyFilter implements Filter {

        public void destroy() {
            System.out.println("Stopping filter");
        }

        public void doFilter(ServletRequest request, ServletResponse response,
                             FilterChain chain) throws IOException, ServletException {
            System.out.println("doFilter called with: " + request);

            chain.doFilter(request, response);
        }

        public void init(FilterConfig filterConfig) throws ServletException {
            Enumeration<String> enums = filterConfig.getInitParameterNames();

            while (enums.hasMoreElements()) {
                String param = (String) enums.nextElement();
                System.out.println(param + ":" + filterConfig.getInitParameter(param));
            }
        }

    }

}
