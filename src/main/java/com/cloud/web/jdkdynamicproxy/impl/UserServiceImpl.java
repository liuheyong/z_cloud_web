package com.cloud.web.jdkdynamicproxy.impl;

import com.cloud.web.jdkdynamicproxy.myinterface.UserService;

/**
 * @author: liuheyong
 * @create: 2020-01-12
 * @description:
 */
public class UserServiceImpl implements UserService {

    public void query() {
        System.out.println("查询用户信息");
    }

    @Override
    public void query2() {
        System.out.println("查询用户信息2");
    }

}
