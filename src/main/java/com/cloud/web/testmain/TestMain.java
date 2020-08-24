package com.cloud.web.testmain;

/**
 * @author: HeYongLiu
 * @create: 07-17-2019
 * @description:
 **/
public class TestMain {

    public static void main(String[] args) {

        //Lock lock = new ReentrantLock();
        //static int[] value = new int[]{1, 2};
        //static AtomicIntegerArray ai = new AtomicIntegerArray(value);
        //Lock lock = new ReentrantLock();
        //ai.getAndSet(0, 3);
        //System.out.println(ai.get(0));
        //List<String> list1 = new ArrayList();
        //List<String> list2 = null;
        //    Optional.ofNullable(list1).orElseThrow(()->new RuntimeException("error1"));
        //    Optional.ofNullable(list2).orElseThrow(()->new RuntimeException("error2"));

        //private void dontStop() {
        //    while (true) {
        //    }
        //}
        //
        //public void stackLeakByThread() {
        //    while (true) {
        //        Thread thread = new Thread(new Runnable() {
        //            @Override
        //            public void run() {
        //                dontStop();
        //            }
        //        });
        //        thread.start();
        //    }
        //}
        //
        //public static void main(String[] args) {
        //    Method method = null;
        //    if (Object.class.equals(SortAlgorithm.class)) {
        //        try {
        //            System.out.println("1");
        //        } catch (Throwable t) {
        //        }
        //    }

        /*SortAlgorithm test = new SortAlgorithm();
        test.stackLeakByThread();*/

        /*ArrayList<SortAlgorithm> arrayList = new ArrayList<SortAlgorithm>();
        while (true) {
            arrayList.add(new SortAlgorithm());
        }*/

        /* Student student = new Student("liu", 99);
        System.out.println(student);*/

       /*String str2 = new String("str") + new String("01");
        String str1 = "str01";
        str2.intern();
        System.out.println(str2.equals(str1));
        System.out.println(str2 == str1);*/
    }
}


