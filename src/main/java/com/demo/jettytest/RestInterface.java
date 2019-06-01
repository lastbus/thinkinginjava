package com.demo.jettytest;

import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * @author make
 * @creare 25/09/2018
 */

@Path("/")
public class RestInterface {

    /**
     * 根据id查询任务信息.
     *
     * @param id 任务id
     * @return 任务信息
     */
    @Path("/getTaskInfo/{id}") // 大括号里的是参数名，在函数位置使用@PathParam注解映射
    @GET // 声明这个接口必须GET访问
    @Produces(MediaType.APPLICATION_JSON) // 声明这个接口将以json格式返回
    public TaskInfoPo getTaskInfo(@PathParam("id") int id) {
        TaskInfoPo taskInfoPo = new TaskInfoPo();
        taskInfoPo.setId(id);
        taskInfoPo.setAppId(1);
        return taskInfoPo;
    }

    /**
     * 根据taskInfo查询subTaskInfo.
     *
     * @param taskInfoPo taskInfo
     * @return subTaskInfo
     */
    @Path("/getSubTaskInfo/") // url上没有参数，参数通过body传入
    @POST
    @Consumes(MediaType.APPLICATION_JSON) // 声明传入参数是json格式
    @Produces(MediaType.APPLICATION_JSON)
    public TaskInfoPo getSubTaskInfo(TaskInfoPo taskInfoPo) {
        TaskInfoPo subTaskInfoPo = new TaskInfoPo();
        subTaskInfoPo.setId((int) System.currentTimeMillis());
        subTaskInfoPo.setTaskId(taskInfoPo.getId());
        subTaskInfoPo.setCreateTime(new Date());

        return subTaskInfoPo;
    }

    /**
     * 测试用的main函数.
     */
    public static void main(String[] args) throws Exception {
        Server server = new Server(8282); // 监听8282端口
        ServletHolder servlet = new ServletHolder(ServletContainer.class);
        // 设置初始化参数
        servlet.setInitParameter("com.sun.jersey.config.property.resourceConfigClass", "com.sun.jersey.api.core.PackagesResourceConfig");
        servlet.setInitParameter("com.sun.jersey.config.property.packages", "com.tencent.awake.data.processing");
        servlet.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true"); // 自动将对象映射成json返回
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");
        handler.addServlet(servlet, "/*");
        server.setHandler(handler);
        server.start();
        System.out.println("start...in 8282");
    }
}