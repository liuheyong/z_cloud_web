package com.cloud.web.algorithm.rediscacheisolation;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: wenyixicodedog
 * @create: 2020-07-25
 * @description:
 */
public class LinkHashMapLRU extends LinkedHashMap<String, String> {

    public LinkHashMapLRU(int initialCapacity, float loadFactor, boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
    }

    public LinkHashMapLRU() {

    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
        //当map的容量大于等于5的时候,再插入新元素就移除旧的元素
        return this.size() >= 5;
    }

    public static void main(String[] args) {
        LinkHashMapLRU map = new LinkHashMapLRU(5, 0.75F, true);
        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");
        map.put("4", "four");
        System.out.println("================原始数据===================");
        print(map);
        map.get("3");
        System.out.println("================访问一次3===================");
        print(map);

        map.get("1");
        map.get("2");
        System.out.println("===============访问一次1、2=====================");
        print(map);
        System.out.println("===============新添加元素5=====================");
        map.put("5", "five");
        print(map);
    }

    static void print(LinkedHashMap<String, String> map) {
        map.keySet().iterator().forEachRemaining(System.out::println);
    }

}
