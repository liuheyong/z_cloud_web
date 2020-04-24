package com.cloud.web.algorithm.leecode;

/**
 * @author: LiuHeYong
 * @create: 2020-04-09
 * @description: 设计问题
 */
public class algorithmPracticeDesign {

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: Shuffle an Array（打乱一个没有重复元素的数组 洗牌）
     */
    //#include<cstdlib>
    //#include<algorithm>
    //class Solution {
    //    private:
    //    vector<int> original; //定义成员变量
    //    vector<int> array;
    //    public:
    //
    //    Solution(vector<int> nums) //?这里为什么不用引用
    //    {
    //        srand(time(nullptr)); // 以当前时间为随机生成器的种子，这里要加，不加速度很慢（猜想可前没能是如果rand()之有srand,则每次都会运行srand(1)比较耗时)
    //        original = nums; //这里为深拷贝
    //        array = nums;
    //    }
    //
    //    /**
    //     * Resets the array to its original configuration and return it.
    //     */
    //    vector<int> reset() {
    //        return original;
    //    }
    //
    //    /**
    //     * Returns a random shuffling of the array.
    //     */
    //    vector<int> shuffle() {
    //        int i, j;
    //        for (i = array.size() - 1; i > 0; i--) //从后往前扫描
    //        {
    //            j = rand() % (i + 1); //产生0~i的随机数(！！注意要产生0~i的随机数，而不是0~i-1,因为要包换不交换的情况）
    //            swap(array[i], array[j]); //用当前数与随机选择的数进行交换
    //        }
    //        return array;
    //    }
    //};

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 最小栈（设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈 用两个栈实现）
     */
    //class MinStack {
    //    public:
    //    /** initialize your data structure here. */
    //    //用两个栈来操作，其中一个把最小元素放到栈顶
    //    MinStack() {}
    //    void push(int x) {
    //        if(mins.empty() || x<=mins.top())
    //            mins.push(x);
    //        s.push(x);
    //    }
    //    void pop() {
    //        if(s.top() == mins.top())
    //            mins.pop();
    //        s.pop();
    //    }
    //    int top() {
    //        return s.top();
    //    }
    //    int getMin() {
    //        return mins.top();
    //    }
    //    private:
    //    stack<int> s,mins;
    //};

}
