package com.demo.jettytest;

import com.alibaba.fastjson.JSONObject;
import com.sun.deploy.net.HttpResponse;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.*;
import org.eclipse.jetty.util.resource.Resource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author make
 * @creare 22/09/2018
 */
public class HelloWorld extends AbstractHandler {

    public static void main(String[] args) throws Exception {

        Server server = new Server(8080);
//        server.setHandler(new HelloWorld());
        ContextHandler rootContextHandler = new ContextHandler();
        rootContextHandler.setContextPath("/");

        rootContextHandler.setHandler(new AbstractHandler() {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
                System.out.println("target: " + target);
                System.out.println("baseRequest: " + baseRequest);
                System.out.println("request: " + request);

                response.setContentType("text/html; charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                PrintWriter writer = response.getWriter();
                writer.println("<h1>root context</h1>");
                baseRequest.setHandled(true);

                System.out.println("an response: " + response);
            }
        });

        ContextHandler makeContexHandler = new ContextHandler();
        makeContexHandler.setContextPath("/make");
        makeContexHandler.setHandler(new AbstractHandler() {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
                System.out.println("target: " + target);
                System.out.println("baseRequest: " + baseRequest);
                System.out.println("request: " + request);

//                response.setContentType("text/html; charset=utf-8");
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_OK);
                PrintWriter writer = response.getWriter();
                JSONObject json = new JSONObject();
                json.put("abc", "123");
                writer.println(json.toJSONString());
//                writer.println("<h1>make context</h1>");
                baseRequest.setHandled(true);

                System.out.println("an response: " + response);
            }
        });

        ResourceHandler rh0 = new ResourceHandler();
        ContextHandler context0 = new ContextHandler();
        context0.setContextPath("/fileserver");
        context0.setBaseResource(Resource.newResource("/Users/make/IdeaProjects/fileserver/web"));
        context0.setHandler(rh0);


        ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
        contextHandlerCollection.setHandlers(new Handler[] {rootContextHandler, makeContexHandler, context0});

        server.setHandler(contextHandlerCollection);
        server.start();
        server.dumpStdErr();
        server.join();
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("target: " + target);
        System.out.println("baseRequest: " + baseRequest);
        System.out.println("request: " + request);


        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer = response.getWriter();
        writer.println("<h1>Hello, world: " + target + "</h1>");
        baseRequest.setHandled(true);

        System.out.println("response: " + response);

    }
}

