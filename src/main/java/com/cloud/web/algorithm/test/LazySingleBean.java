package com.cloud.web.algorithm.test;

/**
 * @author: heyongliu
 * @date: 2021/11/1
 * @description:
 */
public class LazySingleBean {

    private static volatile LazySingleBean bean;

    private LazySingleBean() {
    }

    public static LazySingleBean getBean() {
        if (null == bean) {
            synchronized (LazySingleBean.class) {
                if (null == bean) {
                    bean = new LazySingleBean();
                }
            }
        }
        return bean;
    }
}
