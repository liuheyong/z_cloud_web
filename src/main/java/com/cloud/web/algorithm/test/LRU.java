package com.cloud.web.algorithm.test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: heyongliu
 * @date: 2021/11/1
 * @description:
 */
public class LRU {

    private static LRUClass<Integer, Integer> lruClass = new LRUClass<>();

    public static Integer get(int key) {
        Integer value = lruClass.get(key);
        if (value != null) {
            lruClass.remove(key);
            lruClass.put(key, value);
            return value;
        }
        return null;
    }

    public static Integer set(int key, int value) {
        Integer value_ = lruClass.get(key);
        if (value_ != null) {
            lruClass.remove(key);
            lruClass.put(key, value);
            return value;
        }
        lruClass.put(key, value);
        return value;
    }

    private static class LRUClass<K, V> extends LinkedHashMap<K, V> {
        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > 5;
        }
    }
}
