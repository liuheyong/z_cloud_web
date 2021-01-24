package com.cloud.web.jdkdynamicproxy.main;

import com.cloud.web.algorithm.muke.SortAlgorithm;
import com.cloud.web.jdkdynamicproxy.impl.UserServiceImpl;
import com.cloud.web.jdkdynamicproxy.invocationhandler.MyInvocationHandler;
import com.cloud.web.jdkdynamicproxy.myinterface.UserService;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

/**
 * @author: liuheyong
 * @create: 2020-01-12
 * @description:
 */
public class TestMain {

    public static void main(String[] args) {
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(new UserServiceImpl());
        Object o = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{UserService.class},
                myInvocationHandler);
        ((UserService) o).query();

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
    }

}
