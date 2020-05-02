package com.cloud.web.algorithm.stackdoubleend;

import java.util.Arrays;

/**
 * @author: LiuHeYong
 * @create: 2020-05-02
 * @description: 双端栈(数组头和尾都为栈底，两个栈指针往中间走)
 */
public class ArrayStackDoubleEnd<E> implements Stack<E> {

    public static final int L = 0;  //左端栈标记
    public static final int R = 1;  //右端栈标记
    private E[] data;    //元素容器(两个栈公共的地址空间)
    private int left, right;//左右端栈顶
    private int size;  //栈中元素的总个数

    public ArrayStackDoubleEnd() {
        this(10);
    }

    public ArrayStackDoubleEnd(int capacity) {
        if (capacity < 0) {
            System.out.println("长度不能为负数");
        }
        data = (E[]) new Object[capacity];
        left = -1;
        right = data.length;
        size = 0;
    }

    //自定义左右栈操作
    public void push(int which, E e) {
        if (size == data.length) {  //满 size==data.length left+1=right
            throw new IllegalArgumentException("栈已满！");
        }
        if (which == L) {
            data[++left] = e;
        } else {
            data[--right] = e;
        }
        size++;
    }

    public E pop(int which) {
        if (isEmpty(which)) {
            throw new IllegalArgumentException("栈已空");
        }
        size--;
        if (which == L) {
            return data[left--];
        } else {
            return data[right++];
        }
    }

    public int getCapacity() {
        return data.length;
    }

    public int getSize(int which) {
        if (which == L) {
            return left + 1;
        } else {
            return data.length - right;
        }
    }

    public E peek(int which) {
        if (isEmpty(which)) {
            throw new IllegalArgumentException("栈为空！");
        }
        if (which == L) {
            return data[left];
        } else {
            return data[right];
        }
    }

    public boolean isEmpty(int which) {
        if (which == L) {
            return left == -1;
        } else {
            return right == data.length;
        }
    }

    public void clear(int which) {
        if (which == L) {
            left = -1;
        } else {
            right = data.length;
        }
    }

    //实现部分
    @Override
    public void push(E e) {
        if (size == data.length) {
            throw new IllegalArgumentException("栈已满！");
        }
        if (getSize(L) <= getSize(R)) {
            push(L, e);
        } else {
            push(R, e);
        }
    }

    @Override
    public E pop() {
        if (getSize(L) >= getSize(R)) {
            return pop(L);
        } else {
            return pop(R);
        }
    }

    @Override
    public E peek() {
        if (getSize(L) >= getSize(R)) {
            return peek(L);
        } else {
            return peek(R);
        }
    }

    @Override
    public boolean isEmpty() {
        return left == -1 && right == data.length && size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        left = -1;
        right = data.length;
        size = 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}
