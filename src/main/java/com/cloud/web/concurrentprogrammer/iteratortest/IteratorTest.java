package com.cloud.web.concurrentprogrammer.iteratortest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: wenyixicodedog
 * @create: 2020-08-19
 * @description:
 */
public class IteratorTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        //for(int i=list.size()-1;i>=0;i--){
        //    if(list.get(i).equals("b"))
        //        list.remove(i);}
        Iterator<String> it = list.iterator();
        while(it.hasNext()){
            String x = it.next();
            if(x.equals("b")){
                it.remove();
            }
        }
        System.out.println(list);
    }

}
