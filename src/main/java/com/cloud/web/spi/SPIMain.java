package com.cloud.web.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author: liuheyong
 * @create: 2019-12-07
 * @description:
 */
public class SPIMain {

    public static void main(String[] args) {

        // ServiceLoader实现了Iterable接口，可以遍历出所有的服务实现者
        ServiceLoader<SPIService> serviceLoaders = ServiceLoader.load(SPIService.class);

        /*
         * 方法1： 迭代器
         */
        Iterator<SPIService> spiServiceIterator = serviceLoaders.iterator();
        while (spiServiceIterator != null && spiServiceIterator.hasNext()) {
            SPIService spiService = spiServiceIterator.next();
            System.out.println(spiService.getClass().getName() + " : " + spiService.say());
        }

        /*
         * 迭代方法2： foreach
         */
        for (SPIService spiService : serviceLoaders) {
            System.out.println(spiService.getClass().getName() + " : " + spiService.say());
        }
    }

}
