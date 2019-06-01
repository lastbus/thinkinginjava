package com.demo.jettytest;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import sun.misc.Unsafe;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author make
 * @creare 24/09/2018
 */
@Path("/hello")
public class RestAPITest {

    static ThreadLocal<SimpleDateFormat> dfs = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        }
    };

    @GET
    @Path("{name}")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "data_type", paramType = "body")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public String hello(String body, @Context HttpHeaders headers, @Context UriInfo ui,
                        @ApiParam(required = true) @PathParam("name") String name) throws Exception {

        System.out.println(body);
        if (!headers.getCookies().containsKey("abc")) {
            headers.getCookies().put("abc", new Cookie("k", "v"));
        }
        System.out.println(headers.getCookies());
        System.out.println(headers.getRequestHeader("dataType"));

        System.out.println(ui.getAbsolutePath());

        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("time", dfs.get().format(new Date()));
        System.out.println(addressOf(dfs.get()));
        return json.toJSONString();
    }

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



    private static Unsafe unsafe;  
              
                static {  
        try {  
            Field field = Unsafe.class.getDeclaredField("theUnsafe");  
            field.setAccessible(true);  
            unsafe = (Unsafe) field.get(null);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  

    public static long addressOf(Object o) throws Exception {
          
        Object[] array = new Object[] { o };  
  
        long baseOffset = unsafe.arrayBaseOffset(Object[].class);  
        int addressSize = unsafe.addressSize();  
        long objectAddress;  
        switch (addressSize) {  
        case 4:  
            objectAddress = unsafe.getInt(array, baseOffset);  
            break;  
        case 8:  
            objectAddress = unsafe.getLong(array, baseOffset);  
            break;  
        default:  
            throw new Error("unsupported address size: " + addressSize);  
        }  
        return (objectAddress);  
    }  

    
}
