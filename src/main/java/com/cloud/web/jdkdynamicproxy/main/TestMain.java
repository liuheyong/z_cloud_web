package com.cloud.web.jdkdynamicproxy.main;

import com.cloud.web.jdkdynamicproxy.myinterface.UserService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: liuheyong
 * @create: 2020-01-12
 * @description:
 */
public class TestMain {

    public static void main(String[] args) {
        //MyInvocationHandler myInvocationHandler = new MyInvocationHandler(new UserServiceImpl());
        //Object o = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{UserService.class},
        //        myInvocationHandler);
        //((UserService) o).query();

        //Class<?>[] interfaces = new Class[]{UserService.class} ;
        //byte[] bytes = ProxyGenerator.generateProxyClass("$proxy1", interfaces);
        //File file = new File("/Users/wenyixicodedog/Idea-workspace/z_cloud_web/src/main/java/com/cloud/web/jdkdynamicproxy
        // /myinterface/proxy.java") ;
        //FileOutputStream fo;
        //try {
        //    fo = new FileOutputStream(file);
        //    fo.write(bytes);
        //    fo.flush();
        //    fo.close();
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
        Object o2 = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{UserService.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return null;
                    }
                });
        ((UserService) o2).query();
        ((UserService) o2).query2();
    }
}
