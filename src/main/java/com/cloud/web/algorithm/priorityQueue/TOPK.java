package com.cloud.web.algorithm.priorityQueue;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author: wenyixicodedog
 * @create: 2020-07-25
 * @description:
 */
public class TOPK {

    public static int[] findTOPK(int[] a, int k) {

        PriorityQueue<Integer> queue = new PriorityQueue<>(k);

        Arrays.stream(a).forEach((item) -> {
            if (queue.size() < k) {
                queue.offer(item);
            } else if (queue.peek() < item) {
                queue.poll();
                queue.offer(item);
            }
        });

        int[] result = new int[k];
        for (int i = 0; !queue.isEmpty(); i++) {
            result[i] = queue.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = new int[]{11, 61, 21, 33, 54, 4, 82, 77, 98};
        System.out.println(Arrays.toString(findTOPK(a, 3)));
    }

}
