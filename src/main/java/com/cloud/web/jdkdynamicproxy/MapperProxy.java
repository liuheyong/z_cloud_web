//package com.cloud.web.jdkdynamicproxy;
//
//import com.cloud.commons.dto.Student;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//
///**
// * @author: HeYongLiu
// * @create: 08-15-2019
// * @description:
// **/
//public class MapperProxy implements InvocationHandler {
//
//    @SuppressWarnings("unchecked")
//    public <T> T newInstance(Class<T> clz) {
//        return (T) Proxy.newProxyInstance(clz.getClassLoader(), new Class[]{clz}, this);
//    }
//
//    @Override
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        if (Object.class.equals(method.getDeclaringClass())) {
//            try {
//                // 诸如hashCode()、toString()、equals()等方法，将target指向当前对象this
//                return method.invoke(this, args);
//            } catch (Throwable t) {
//            }
//        }
//        return new Student((Integer) args[0], 18,"zhangsan" );
//    }
//
//}
