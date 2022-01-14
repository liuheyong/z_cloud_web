package com.cloud.web.loadbalance.entity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author: heyongliu
 * @date: 2022/1/10
 * @description: 一致性hash选择器
 */
public class ConsistentHashSelector {

    // 使用 TreeMap 存储 虚拟节点
    public final TreeMap<Long, LBDto> virtualInvokers;

    public final int replicaNumber;

    public final int identityHashCode;

    public ConsistentHashSelector(List<LBDto> lbDtos, int identityHashCode) {
        this.virtualInvokers = new TreeMap<>();
        // 获取虚拟节点数，默认为160
        this.replicaNumber = 160;
        this.identityHashCode = identityHashCode;
        for (LBDto lbDto : lbDtos) {
            String address = lbDto.getHostPort();
            for (int i = 0; i < replicaNumber / 4; i++) {
                // 对 address + i 进行 md5 运算，得到一个长度为16的字节数组
                byte[] digest = md5(address + i);
                for (int h = 0; h < 4; h++) {
                    // h = 0 时，取 digest 中下标为 0 ~ 3 的4个字节进行位运算
                    // h = 1 时，取 digest 中下标为 4 ~ 7 的4个字节进行位运算
                    // h = 2, h = 3 时过程同上
                    long m = hash(digest, h);
                    // 将hash映射关系存储到 virtualInvokers 中，
                    // virtualInvokers 需要提供高效的查询操作，因此选用 TreeMap 作为存储结构
                    virtualInvokers.put(m, lbDto);
                }
            }
        }
    }

    /**
     * 根据参数进行一致性hash
     *
     * @author: heyongliu
     * @date: 2022/1/14
     */
    public LBDto select(String hashArguments) {
        byte[] digest = md5(hashArguments);
        return selectForKey(hash(digest, 0));
    }

    private LBDto selectForKey(long hash) {
        Long key = hash;
        if (!virtualInvokers.containsKey(key)) {
            SortedMap<Long, LBDto> tailMap = virtualInvokers.tailMap(key);
            if (tailMap.isEmpty()) {
                key = virtualInvokers.firstKey();
            } else {
                key = tailMap.firstKey();
            }
        }
        return virtualInvokers.get(key);
    }

    private long hash(byte[] digest, int number) {
        return (((long) (digest[3 + number * 4] & 0xFF) << 24)
                | ((long) (digest[2 + number * 4] & 0xFF) << 16)
                | ((long) (digest[1 + number * 4] & 0xFF) << 8)
                | (digest[number * 4] & 0xFF))
                & 0xFFFFFFFFL;
    }

    private byte[] md5(String value) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        md5.reset();
        byte[] bytes;
        bytes = value.getBytes(StandardCharsets.UTF_8);
        md5.update(bytes);
        return md5.digest();
    }
}
