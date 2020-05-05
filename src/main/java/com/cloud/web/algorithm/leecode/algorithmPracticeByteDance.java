package com.cloud.web.algorithm.leecode;

import com.cloud.web.algorithm.muke.SortAlgorithm;
import com.cloud.web.web.DefaultController;

import java.util.*;

/**
 * @author: LiuHeYong
 * @create: 2020-04-09
 * @description: ByteDance部分
 */
public class algorithmPracticeByteDance extends DefaultController {

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

    /**
     * @Date: 2020-05-01
     * @Param:
     * @return:
     * @Description: 字符串的排列（给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列）
     */
    public static boolean checkInclusion(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        int[] c1 = new int[26];
        int[] c2 = new int[26];
        for (char c : s1.toCharArray()) c1[c - 'a']++;
        for (int i = 0; i < l2; i++) {
            if (i >= l1) --c2[s2.charAt(i - l1) - 'a'];
            c2[s2.charAt(i) - 'a']++;
            if (Arrays.equals(c1, c2)) return true;
        }
        return false;
    }

    /**
     * @Date: 2020-05-02
     * @Param:
     * @return:
     * @Description: 翻转字符串里的单词(给定一个字符串, 逐个翻转字符串中的每个单词)
     */
    public static String reverseWords(String s) {
        s = s.trim();
        if (s == null || s.length() == 0) {
            return "";
        }
        String allStr = "";
        //入栈操作
        int n = s.length();
        Stack stack = new Stack();
        String str = "";
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != 32) {
                str += String.valueOf(s.charAt(i));
            }
            if (s.charAt(i) == 32 && s.charAt(i + 1) != 32) {
                stack.push(str);
                str = "";
            }
        }
        stack.push(str);
        //stack.stream().forEach(item->{System.out.println(item);});
        while (!stack.empty()) {
            str = String.valueOf(stack.pop());
            for (int i = 0; i < str.length(); i++) {
                allStr += String.valueOf(str.charAt(i));
            }
            allStr += " ";
        }
        return allStr.trim();
    }

    /**
     * @Date: 2020-05-02
     * @Param:
     * @return:
     * @Description: 三数之和(数组 nums ， 判断 nums 中是否存在三个元素 a ， b ， c ， 使得 a + b + c = 0)
     * 未完待续
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int n = nums.length;
        Set<List<Integer>> returnList = new HashSet<>();

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 2; j < n; j++) {
                if (nums[i] + nums[i + 1] + nums[j] == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[i + 1]);
                    list.add(nums[j]);
                    returnList.add(list);
                }
            }

        }
        return new ArrayList<>(returnList);
    }

    /**
     * @Date: 2020-05-02
     * @Param:
     * @return:
     * @Description: 三数之和(fix一个元素 ， 找另外两个元素之和等于第一个元素的相反数)
     * 未完待续
     */
    //class Solution {
    //    public:
    //    vector<vector<int>> threeSum(vector<int>& nums) {
    //        vector<vector<int>> res;
    //        sort(nums.begin(), nums.end());
    //        if (nums.empty() || nums.back() < 0 || nums.front() > 0) return {};
    //        for (int k = 0; k < nums.size(); ++k) {
    //            if (nums[k] > 0) break;
    //            if (k > 0 && nums[k] == nums[k - 1]) continue;
    //            int target = 0 - nums[k];
    //            int i = k + 1, j = nums.size() - 1;
    //            while (i < j) {
    //                if (nums[i] + nums[j] == target) {
    //                    res.push_back({nums[k], nums[i], nums[j]});
    //                    while (i < j && nums[i] == nums[i + 1]) ++i;
    //                    while (i < j && nums[j] == nums[j - 1]) --j;
    //                    ++i; --j;
    //                } else if (nums[i] + nums[j] < target) ++i;
    //                else --j;
    //            }
    //        }
    //        return res;
    //    }
    //};

    /**
     * @Date: 2020-05-02
     * @Param:
     * @return:
     * @Description: 岛屿的最大面积
     * 未完待续
     */
    public int maxAreaOfIsland(int[][] grid) {
        int result = 0;
        if (grid == null || grid.length == 0) {
            return result;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    int count = dfs(grid, i, j);
                    result = Math.max(result, count);
                }
            }
        }
        return result;
    }

    /**
     * @Date: 2020-05-02
     * @Param:
     * @return:
     * @Description: 岛屿的最大面积
     */
    private int dfs(int[][] grid, int row, int col) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] == 0) {
            return 0;
        }
        grid[row][col] = 0;
        return dfs(grid, row + 1, col) + dfs(grid, row - 1, col) + dfs(grid, row, col + 1) + dfs(grid, row, col - 1) + 1;
    }

    /**
     * @Date: 2020-05-02
     * @Param:
     * @return:
     * @Description: 搜索旋转排序数组
     */
    public int search(int[] nums, int target) {
        //双指针法
        if (nums.length == 0) return -1;
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            //二分法，分情况
            int mid = (i + j) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[mid] >= nums[i]) {
                if (nums[i] <= target && target < nums[mid])
                    j = mid - 1;
                else
                    i = mid + 1;
            } else {
                if (nums[mid] < target && target <= nums[j])
                    i = mid + 1;
                else
                    j = mid - 1;
            }
        }
        return -1;
    }

    /**
     * @Date: 2020-05-02
     * @Param:
     * @return:
     * @Description: 最长连续递增序列（双指针枚举法）
     */
    public static int findLengthOfLCIS1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int maxLength = 1;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (j + 1 < n && nums[j] < nums[j + 1]) {
                    maxLength++;
                } else {
                    break;
                }
            }
            a[i] = maxLength;
            maxLength = 1;
        }
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > count) {
                count = a[i];
            }
        }
        return count;
    }

    /**
     * @Date: 2020-05-02
     * @Param:
     * @return:
     * @Description: 最长连续递增序列（单指针枚举法）
     */
    public int findLengthOfLCIS2(int[] nums) {
        if (nums.length == 0)
            return 0;
        int max = 0;
        int count = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                count++;
            } else {
                max = Math.max(count, max);
                count = 1;
            }
        }
        max = Math.max(count, max);
        return max;
    }

    /**
     * @Date: 2020-05-02
     * @Param:
     * @return:
     * @Description: 最长连续递增序列（分治法）
     */
    public static int findLengthOfLCIS3(int[] nums, int left, int right) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int leftCount = 0;
        int rightCount = 0;
        int mergeCount = 0;
        int mid = left + right >> 1;
        //left < right 递归出口
        if (left < right) {
            leftCount = findLengthOfLCIS3(nums, left, mid);
            rightCount = findLengthOfLCIS3(nums, mid + 1, right);
            mergeCount = mergeCount(nums, left, mid, right);
        }
        return maxInThreeNum(leftCount, rightCount, mergeCount);
    }

    public static int mergeCount(int[] nums, int left, int mid, int right) {
        int leftCount = 0;
        for (int i = mid; i >= left; i--) {
            if (i - 1 >= left && nums[i] > nums[i - 1]) {
                leftCount++;
            }
        }
        int rightCount = 0;
        for (int i = mid + 1; i <= right; i++) {
            if (i + 1 <= right && a[i] < a[i + 1]) {
                rightCount++;
            }
        }
        return leftCount + rightCount;
    }

    /**
     * @Date: 2020-05-02
     * @Param:
     * @return:
     * @Description: 最长连续递增序列（动态规划）
     */
    public static int findLengthOfLCIS4(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] C = new int[n];
        int[] Rec = new int[n];
        C[0] = 1;
        Rec[0] = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                C[i] = C[i - 1] + 1;
                Rec[i] = Rec[i - 1];
            } else {
                C[i] = 1;
                Rec[i] = i;
            }
        }
        int count = 0;
        int index = 0;
        for (int i = 0; i < C.length; i++) {
            if (C[i] > count) {
                count = C[i];
                index = i;
            }
        }
        System.out.println("开始下标和结束下标为：" + Rec[index] + " " + (count - Rec[index] - 1));
        return count;
    }

    /**
     * @Date: 2020-05-04
     * @Param:
     * @return:
     * @Description: 数组中的第K个最大元素
     */
    public static int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //将第K个最大元素转换为第l个最小元素
        int l = nums.length + 1 - k;
        return findKthLargestWithIndex(nums, 0, nums.length - 1, l);
    }

    public static int findKthLargestWithIndex(int[] nums, int left, int right, int l) {
        int element = 0;
        int p = SortAlgorithm.randomPartitionEx(nums, left, right);
        if (p - left + 1 == l) {
            element = nums[p];
        }
        if (p - left + 1 > l) {
            element = findKthLargestWithIndex(nums, left, p, l);
        }
        if (p - left + 1 < l) {
            element = findKthLargestWithIndex(nums, p + 1, right, l - (p - left + 1));
        }
        return element;
    }

    /**
     * @Date: 2020-05-04
     * @Param:
     * @return:
     * @Description: 最长连续序列
     */
    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < min) {
                min = nums[i];
                minIndex = i;
            }
        }
        int[] C = new int[n];
        C[minIndex] = 1;
        Integer[] numsInteger = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] == min) {
                continue;
            }
            int preIndex = new ArrayList<>(Arrays.asList(numsInteger)).indexOf(nums[i] - 1);
            if (preIndex != -1) {
                C[i] = C[preIndex] + 1;
            } else {
                C[i] = 1;
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (C[i] > max) {
                max = C[i];
            }
        }
        return max;
    }

    /*
     *  哈希表和线性空间构造
     *  时间复杂度O(n)   空间复杂度O(n)
     */
    //class Solution {
    //    public:
    //    int longestConsecutive(vector<int>& nums) {
    //        if(nums.size() < 2) return nums.size();
    //        set<int> s;
    //        for(auto num:nums) s.insert(num);   // 构建集合
    //
    //        int maxLen = 0, currentLen = 0;
    //        for (int num:s) {
    //            // 只检查num-1不在集合中的元素
    //            if(s.count(num-1) == 0){
    //                int currentNum = num;
    //                currentLen = 1;
    //
    //                while (s.count(currentNum+1)){  // 往后查找元素是否存在
    //                    currentLen ++;
    //                    currentNum ++;
    //                }
    //                maxLen = max(maxLen,currentLen);
    //            }
    //        }
    //
    //        return maxLen;
    //    }
    //};

    static int[] a = {100, 4, 200, 1, 3, 2};

    public static void main(String[] args) throws Exception {

        System.out.println(longestConsecutive(a));

        //System.out.println(findKthLargest(a, 4));

        //System.out.println(findLengthOfLCIS4(a));

        //System.out.println(findLengthOfLCIS3(a,0,4));

        //System.out.println(findLengthOfLCIS1(a));

        //System.out.println(threeSum(a));

        //System.out.println(reverseWords("   the   sky    is   blue   "));

        //System.out.println(checkInclusion("aab", "paabp"));

        //System.out.println(lengthOfLongestSubstring4("bdvdfp"));

        //System.out.println(lengthOfLongestSubstring1("bdvdfp"));

    }

}
