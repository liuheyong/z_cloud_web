package com.cloud.web.algorithm.leecode;

/**
 * @author: LiuHeYong
 * @create: 2020-04-09
 * @description: 排序和搜索
 */
public class algorithmPracticeSortAndSearch {

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 合并两个有序数组
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 确定指针位置
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;
        while ((p1 >= 0) && (p2 >= 0)) {
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
        }
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

    public static void main(String[] args) throws Exception {


    }

}
