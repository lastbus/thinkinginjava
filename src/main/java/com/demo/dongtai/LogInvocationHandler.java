package com.demo.dongtai;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author make
 * @creare 16/10/2018
 */

public class LogInvocationHandler implements InvocationHandler {

    public static void main(String[] args) {
        LogInvocationHandler log = new LogInvocationHandler(new HelloImp());

        Hello hello = (Hello) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(), // 1. 类加载器
                new Class<?>[] { Hello.class }, // 2. 代理需要实现的接口，可以有多个
                new LogInvocationHandler(new HelloImp()));// 3. 方法调用的实际处理者
        System.out.println(hello.sayHello("I love you!"));

    }

    private Hello hello;
    public LogInvocationHandler(Hello hello) {
        this.hello = hello;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("sayHello".equals(method.getName())) {
            System.out.println("You said: " + Arrays.toString(args));
        }
        return method.invoke(hello, args);
    }
}

// 接口
interface Hello{
    String sayHello(String str);
}
// 实现
class HelloImp implements Hello{
    @Override
    public String sayHello(String str) {
        return "HelloImp: " + str;
    }
}


