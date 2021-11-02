package com.cloud.web.algorithm.test;

/**
 * @author: heyongliu
 * @date: 2021/11/1
 * @description:
 */
public class EarlySingleBean {

    private static volatile EarlySingleBean bean = new EarlySingleBean();

    private EarlySingleBean() {
    }

    public static EarlySingleBean getBean() {
        return bean;
    }
}
