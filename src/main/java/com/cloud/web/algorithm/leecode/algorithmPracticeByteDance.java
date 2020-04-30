package com.cloud.web.algorithm.leecode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: LiuHeYong
 * @create: 2020-04-09
 * @description: ByteDance部分
 */
public class algorithmPracticeByteDance {

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 无重复字符的最长子串（有漏洞 例如 "bdvdfp"）
     */
    public static int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] C = new int[n];
        int[] Rec = new int[n];
        C[0] = 1;
        Rec[0] = 0;
        HashSet<Character> set = new HashSet<>();
        set.add(s.charAt(0));
        int preSetSize = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                set.add(s.charAt(i));
                if (set.size() == preSetSize + 1) {
                    C[i] = C[i - 1] + 1;
                    Rec[i] = Rec[i - 1];
                    preSetSize++;
                }
            }
            if (s.charAt(i) == s.charAt(i - 1)) {
                set.clear();
                set.add(s.charAt(i));
                preSetSize = 1;
                C[i] = 1;
                Rec[i] = i;
            }
        }
        int max = 0;
        int endIndex = 0;
        for (int i = 0; i < C.length; i++) {
            if (C[i] > max) {
                max = C[i];
                endIndex = i;
            }
        }
        int startIndex = endIndex - max + 1;
        System.out.println("开始下标：".concat(String.valueOf(startIndex)).concat(",").concat("结束下标：").concat(String.valueOf(endIndex)));
        return max;
    }

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 无重复字符的最长子串
     */
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        int res = 0;
        int end = 0, start = 0;
        Set<Character> set = new HashSet<>();
        while (start < n && end < n) {
            if (set.contains(s.charAt(end))) {
                set.remove(s.charAt(start++));
            } else {
                set.add(s.charAt(end++));
                res = Math.max(res, end - start);
            }

        }
        return res;
    }

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 无重复字符的最长子串
     */
    public int lengthOfLongestSubstring3(String s) {
        int n = s.length();
        int res = 0;
        int end = 0, start = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (; start < n && end < n; end++) {
            if (map.containsKey(s.charAt(end))) {
                start = Math.max(map.get(s.charAt(end)), start);//从有重复的下一个位置继续找
            }
            map.put(s.charAt(end), end + 1);//map每次更新
            res = Math.max(res, end - start + 1);//结果每次更新
        }
        return res;
    }

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 无重复字符的最长子串
     */
    public static int lengthOfLongestSubstring4(String str) {
        if (str == null || str.length() == 0)
            return 0;
        //dp数组可以省略，因为只需记录前一位置的dp值即可
        int[] dp = new int[str.length()];
        dp[0] = 1;
        int maxdp = 1;
        for (int dpIndex = 1; dpIndex < dp.length; dpIndex++) {
            //i最终会停在重复字符或者-1的位置,要注意i的结束条件
            int i = dpIndex - 1;
            for (; i >= dpIndex - dp[dpIndex - 1]; i--) {
                if (str.charAt(dpIndex) == str.charAt(i))
                    break;
            }
            dp[dpIndex] = dpIndex - i;
            if (dp[dpIndex] > maxdp)
                maxdp = dp[dpIndex];
        }
        return maxdp;
    }

    public static void main(String[] args) throws Exception {

        System.out.println(lengthOfLongestSubstring4("bdvdfp"));

        //System.out.println(lengthOfLongestSubstring1("bdvdfp"));

    }

}
