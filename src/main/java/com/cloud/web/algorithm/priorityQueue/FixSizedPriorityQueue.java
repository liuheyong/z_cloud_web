package com.cloud.web.algorithm.priorityQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author: LiuHeYong
 * @create: 2020-06-11
 * @description:
 */
public class FixSizedPriorityQueue<E extends Comparable<E>> {
    private PriorityQueue<E> queue;
    private int maxSize;

    public FixSizedPriorityQueue(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException();
        }
        this.maxSize = maxSize;
        //TODO o1.compareTo(o2) 从堆顶到堆底升序 最小堆
        //TODO o2.compareTo(o1) 从堆顶到堆底降序 最大堆
        this.queue = new PriorityQueue<>(maxSize, (((o1, o2) -> o1.compareTo(o2))));
    }

    public void add(E e) {
        // 未达到最大容量，直接添加
        if (queue.size() < maxSize) {
            queue.add(e);
        } else {
            // 队列已满
            E peek = queue.peek();
            //TODO  >0  TOP N
            if (e.compareTo(peek) > 0) {
                queue.poll();
                queue.add(e);
            }
        }
    }

    public List<E> queueData() {
        List<E> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            list.add(queue.poll());
        }
        return list;
    }

}
