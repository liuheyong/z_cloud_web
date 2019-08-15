package com.cloud.web.JDKdynamicproxy;


import com.cloud.commons.dto.Student;

/**
 * @author: HeYongLiu
 * @create: 08-15-2019
 * @description:
 **/
public class TestMain {

    public static void main(String[] args) {
        MapperProxy proxy = new MapperProxy();
        UserMapper mapper = proxy.newInstance(UserMapper.class);
        Student student = mapper.getUserById(1001);
        System.out.println("ID:" + student.getId());
        System.out.println("Name:" + student.getName());
        System.out.println("Age:" + student.getAge());
        System.out.println(mapper.toString());
    }

}
