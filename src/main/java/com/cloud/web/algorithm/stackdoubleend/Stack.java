package com.cloud.web.algorithm.stackdoubleend;

/**
 * @author: LiuHeYong
 * @create: 2020-05-02
 * @description:
 */
public interface Stack<E> {
    public void push(E e); //进栈

    public E pop();//出栈

    public E peek();//获取栈顶元素

    public boolean isEmpty();//判断栈空

    public int getSize();//获取栈有效长度

    public void clear();//清空栈
}
