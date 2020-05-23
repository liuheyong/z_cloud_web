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

    /**
     * @Date: 2020-05-07
     * @Param:
     * @return:
     * @Description: 朋友圈 (深度优先遍历)
     */
    public int findCircleNumDFS(int[][] M) {
        boolean[] visited = new boolean[M.length];
        int res = 0;
        for (int i = 0; i < M.length; i++) {
            if (!visited[i]) {
                DFS(M, i, visited);
                res++;
            }
        }
        return res;
    }

    public void DFS(int[][] M, int k, boolean[] visited) {
        visited[k] = true;
        for (int i = 0; i < M.length; i++) {
            if (!visited[i] && M[k][i] == 1) {
                visited[i] = true;
                DFS(M, i, visited);
            }
        }
    }

    /**
     * @Date: 2020-05-07
     * @Param:
     * @return:
     * @Description: 朋友圈 (广度优先遍历)
     */
    public int findCircleNumBFS(int[][] M) {
        boolean[] visited = new boolean[M.length];
        int res = 0;
        Queue<Integer> queue;
        for (int i = 0; i < M.length; i++) {
            if (!visited[i]) {
                queue = new LinkedList<>();
                queue.add(i);
                BFS(M, queue, visited);
                res++;
            }
        }
        return res;
    }

    public void BFS(int[][] M, Queue<Integer> q, boolean[] visited) {
        while (!q.isEmpty()) {
            int a = q.poll();
            for (int i = 0; i < M.length; i++) {
                if (i != a && M[a][i] == 1 && !visited[i]) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    /**
     * @Date: 2020-05-07
     * @Param:
     * @return:
     * @Description: 合并区间
     */
    public static int[][] merge(int[][] intervals) {
        int len = intervals.length;
        if (len < 2) return intervals;
        int cnt = 0; // 合并次数
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (intervals[i][0] <= intervals[j][1] && intervals[i][1] >= intervals[j][0]) {
                    intervals[j][0] = Math.min(intervals[j][0], intervals[i][0]);
                    intervals[j][1] = Math.max(intervals[j][1], intervals[i][1]);
                    intervals[i] = null; // 清空前者
                    cnt++;
                    break;
                }
            }
        }

        int[][] res = new int[len - cnt][2]; // len - cnt 合并后个数
        int ri = 0;
        for (int[] pair : intervals) {
            if (pair != null) res[ri++] = pair;
        }
        return res;
    }

    /**
     * @Date: 2020-05-09
     * @Param:
     * @return:
     * @Description: 接雨水
     */
    public static int trap(int[] height) {
        int n = height.length;
        int result = 0;
        if (n == 0) {
            return result;
        }
        int maxHeight = height[0];
        for (int i = 1; i < n; i++) {
            if (height[i] > maxHeight) {
                maxHeight = height[i];
            }
        }
        int[] newHeight = new int[n];
        for (int i = 0; i < maxHeight; i++) {
            for (int j = 0; j < n; j++) {
                newHeight[j] = height[j] - i;
            }
            int left = 0;
            int right = n - 1;
            while (left < n && newHeight[left] <= 0) {
                left++;
            }
            while (right >= 0 && newHeight[right] <= 0) {
                right--;
            }
            for (int j = left; j <= right; j++) {
                if (newHeight[j] <= 0) {
                    result++;
                }
            }
        }
        return result;
    }

    public static int trap2(int[] height) {
        int n = height.length;
        int result = 0;
        if (n == 0 || n == 1) {
            return result;
        }
        int left = 0;
        //variable left represents the left border of the area where can contain water
        while (left < n - 1 && height[left + 1] >= height[left]) {
            left++;
        }
        int right = n - 1;
        //variable right represents the right border of the area where can contain water
        while (right >= 1 && height[right - 1] >= height[right]) {
            right--;
        }
        while (left < right) {
            int leftHeight = height[left];
            int rightHeight = height[right];
            if (leftHeight <= rightHeight) {
                while (left < right) {
                    left++;
                    if (height[left] < leftHeight) {
                        result += leftHeight - height[left];
                    } else {
                        break;
                    }
                }
            } else {
                while (left < right) {
                    right--;
                    if (height[right] < rightHeight) {
                        result += rightHeight - height[right];
                    } else {
                        break;
                    }
                }
            }
        }
        return result;
    }

    public static int trap3(int[] height) {
        int n = height.length;
        int result = 0;
        if (n == 0 || n == 1) {
            return result;
        }
        int left = 0;
        int right = n - 1;
        int leftHeight = 0;
        int rightHeight = 0;
        while (left < right) {
            if (height[left] <= height[right]) {
                leftHeight = Math.max(leftHeight, height[left]);
                result += leftHeight - height[left];
                left++;
            } else {
                rightHeight = Math.max(rightHeight, height[right]);
                result += rightHeight - height[right];
                right--;
            }
        }
        return result;
    }

    public static int trap4(int[] height) {
        int n = height.length;
        int result = 0;
        if (n == 0 || n == 1) {
            return result;
        }
        for (int i = 1; i < n - 1; i++) {
            int leftMax = 0;
            for (int j = 0; j < i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }
            int rightMax = 0;
            for (int j = i + 1; j < n; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            int min = Math.min(leftMax, rightMax);
            if (min > height[i]) {
                result += min - height[i];
            }
        }
        return result;
    }

    public static int trap5(int[] height) {
        int n = height.length;
        int result = 0;
        if (n == 0 || n == 1) {
            return result;
        }
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        leftMax[0] = height[0];
        rightMax[n - 1] = height[n - 1];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
            rightMax[n - 1 - i] = Math.max(height[n - 1 - i], rightMax[n - i]);
        }
        for (int i = 1; i < n - 1; i++) {
            int min = Math.min(leftMax[i - 1], rightMax[i + 1]);
            if (min > height[i]) {
                result += min - height[i];
            }
        }
        return result;
    }

    public static int trap6(int[] height) {
        int n = height.length;
        int result = 0;
        if (n == 0 || n == 1) {
            return result;
        }
        int cur = 0;
        Stack<Integer> stack = new Stack<Integer>();
        while (cur < n) {
            while (!stack.isEmpty() && height[cur] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                //distance represents the width
                int distance = cur - stack.peek() - 1;
                //tempHeight represents the height
                int tempHeight = Math.min(height[cur], height[stack.peek()]) - height[top];
                result += tempHeight * distance;
            }
            stack.push(cur);
            cur++;
        }
        return result;
    }

    /**
     * @Date: 2020-05-09
     * @Param:
     * @return:
     * @Description: 两数相加
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 创建结果链表的头节点，默认该节点中的value为-1
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        // 进位标识carry，默认为0
        int carry = 0;
        // 遍历链表，当两个链表都为空时，退出
        while (l1 != null || l2 != null) {
            // 判断该节点是否为空，当结点为空时，用0补齐；不为空时，加数即为节点的值
            int d1 = (l1 == null) ? 0 : l1.val;
            int d2 = (l2 == null) ? 0 : l2.val;
            // 对结点求和，注意：求和是需要考虑到进位
            int sum = d1 + d2 + carry;
            // 更新进位标识
            carry = (sum >= 10) ? 1 : 0;
            // sum%10标识求和的个位数，将其保存到结果链表中
            pre.next = new ListNode(sum % 10);
            pre = pre.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        // 重点，这是一个特殊情况，当两个链表计算完后，
        // 还需要判断进位标识是否为1，如果为1，如23+81=104，需要创建一个结点保存最高位
        if (carry == 1)
            pre.next = new ListNode(1);
        return dummy.next;
    }

    /**
     * @Date: 2020-05-09
     * @Param:
     * @return:
     * @Description: 排序链表
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        sort(head, null);
        return head;
    }

    private void sort(ListNode head, ListNode end) {
        if (head == end || head.next == end) {
            return;
        }
        ListNode pre = head.next;
        ListNode cur = head;
        ListNode cure = head;

        while (pre != end) {
            if (pre.val < cur.val) {
                if (cure.next == pre) {
                    int n = pre.val;
                    pre.val = cur.val;
                    cur.val = n;
                    pre = pre.next;
                    cur = cur.next;
                    cure = cure.next;
                } else {
                    int n = pre.val;
                    pre.val = cur.val;
                    cur.val = n;
                    int v = pre.val;
                    pre.val = cure.next.val;
                    cure.next.val = v;
                    cure = cure.next;
                    cur = cur.next;
                    pre = pre.next;
                }
            } else if (pre.val == cur.val) {
                if (cure.next == pre) {
                    cure = pre;
                    pre = pre.next;
                } else {
                    int n = pre.val;
                    pre.val = cure.next.val;
                    cure.next.val = n;
                    cure = cure.next;
                    pre = pre.next;
                }
            } else {
                pre = pre.next;
            }
        }
        //分治递归求解
        sort(head, cur);
        sort(cure.next, end);
    }

    /**
     * @Date: 2020-05-11
     * @Param:
     * @return:
     * @Description: 相交链表
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
            if (p1 == null) {
                p1 = headB;
            } else {
                p1 = p1.next;
            }
            if (p2 == null) {
                p2 = headA;
            } else {
                p2 = p2.next;
            }
        }
        return p1;
    }

    /**
     * @date: 2020/5/12
     * @param:
     * @return:
     * @exception:
     * @description: 合并K个排序链表
     * 输入:
     * [
     * 1->4->5,
     * 1->3->4,
     * 2->6
     * ]
     * 输出: 1->1->2->3->4->4->5->6
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        ListNode concatNode = lists[0];
        ListNode indexNode = concatNode;
        ListNode indexPre = new ListNode();
        indexPre.next = indexNode;
        for (int i = 1; i < lists.length; i++) {
            while (lists[i] != null) {
                if (lists[i].val > indexNode.val) {
                    indexNode = indexNode.next;
                }
                if (lists[i].val == indexNode.val) {
                    if (indexNode.next != null) {
                        ListNode temNode = indexNode.next;
                        indexNode.next = lists[i];
                        lists[i].next = temNode;
                        indexNode = indexNode.next;
                        lists[i] = lists[i].next;
                    } else {
                        indexNode.next = lists[i];
                        lists[i] = lists[i].next;
                    }
                }
                if (lists[i].val < indexNode.val) {
                    ListNode temNode = lists[i];
                    temNode.next = indexNode;
                    indexPre.next = temNode;
                    lists[i] = lists[i].next;
                }
            }
        }
        return concatNode;
    }

    /**
     * @date: 2020/5/12
     * @param:
     * @return:
     * @exception:
     * @description: 合并2个排序链表
     */
    public static ListNode mergeTwoListNode(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return (l1 == null) ? l2 : l1;
        }
        if (l1.val <= l2.val) {
            l1.next = mergeTwoListNode(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListNode(l1, l2.next);
            return l2;
        }
    }

    /**
     * @date: 2020/5/12
     * @param:
     * @return:
     * @exception:
     * @description: 合并K个排序链表
     */
    public static ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        // 1. 初始化小根堆
        PriorityQueue<ListNode> queue = new PriorityQueue((Comparator<ListNode>) (o1, o2) -> (o1.val - o2.val));
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) queue.add(lists[i]);
        }
        // 2. 取出堆顶元素，并将堆顶元素的下一节点插入小根堆
        ListNode res = new ListNode(0);
        ListNode cur = res;
        while (!queue.isEmpty()) {
            ListNode top = queue.poll();
            if (top.next != null) {
                queue.add(top.next);
            }
            cur.next = top;
            cur = cur.next;
        }
        return res.next;
    }   

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * @date: 2020/5/12
     * @param:
     * @return:
     * @exception:
     * @description: 二叉树的最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    /**
     * @date: 2020/5/12
     * @param:
     * @return:
     * @exception:
     * @description: 二叉树的锯齿形层次遍历
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> lists = new LinkedList<>();
        if (root == null) {
            return lists;
        }
        int flag = 0;
        //0：从左向右，1：从右向左
        List<Integer> layer = new LinkedList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        Deque<TreeNode> nextQueue = new LinkedList<>();
        queue.offerLast(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.pollLast();
            layer.add(node.val);
            if (flag == 0) {
                if (node.left != null) {
                    nextQueue.offerLast(node.left);
                }
                if (node.right != null) {
                    nextQueue.offerLast(node.right);
                }
            } else {
                if (node.right != null) {
                    nextQueue.offerLast(node.right);
                }
                if (node.left != null) {
                    nextQueue.offerLast(node.left);
                }
            }
            if (queue.isEmpty()) {
                Deque<TreeNode> temp = nextQueue;
                nextQueue = queue;
                queue = temp;
                flag = 1 - flag;
                lists.add(layer);
                layer = new LinkedList<>();
            }
        }
        return lists;
    }

    /**
     * @date: 2020/5/12
     * @param:
     * @return:
     * @exception:
     * @description: 最大正方形
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int max = 0, m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        //先初始化 dp数组的第一列
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0] - '0';
            max = Math.max(max, dp[i][0]);
        }
        //再初始化dp数组的第一行
        for (int i = 0; i < n; i++) {
            dp[0][i] = matrix[0][i] - '0';
            max = Math.max(max, dp[0][i]);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = matrix[i][j] == '1' ? Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1 : 0;
                max = Math.max(max, dp[i][j]);
            }
        }
        return max * max;
    }

    /**
     * @date: 2020/5/12
     * @param:
     * @return:
     * @exception:
     * @description: 三角形最小路径和（不能用贪心算法--存在后续有效性）
     */
    public int minimumTotalGreed(List<List<Integer>> triangle) {
        int sum = triangle.get(0).get(0);
        int m = 0, n = 0;
        for (int i = 1; i < triangle.size(); i++) {
            int bottom = triangle.get(m + 1).get(n);
            int rightBottom = triangle.get(m + 1).get(n + 1);
            if (bottom < rightBottom) {
                sum += bottom;
                m = m + 1;
            } else {
                sum += rightBottom;
                m = m + 1;
                n = n + 1;
            }
        }
        return sum;
    }

    /**
     * @date: 2020/5/12
     * @param:
     * @return:
     * @exception:
     * @description: 三角形最小路径和（dfs）
     */
    Integer memo[][];

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0)
            return 0;
        memo = new Integer[triangle.size()][triangle.size()];
        return dfs(triangle, 0, 0);

    }

    private int dfs(List<List<Integer>> triangle, int level, int idx) {
        if (level == triangle.size())
            return 0;
        if (memo[level][idx] != null)
            return memo[level][idx];
        int left = dfs(triangle, level + 1, idx);
        int right = dfs(triangle, level + 1, idx + 1);
        memo[level][idx] = triangle.get(level).get(idx) + Math.min(left, right);
        return triangle.get(level).get(idx) + Math.min(left, right);
    }

    static int[] a = {2, 1, 3};

    public static void main(String[] args) throws Exception {

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(5);
        ListNode l4 = new ListNode(1);
        ListNode l5 = new ListNode(3);
        ListNode l6 = new ListNode(4);
        ListNode l7 = new ListNode(2);
        ListNode l8 = new ListNode(6);
        //1->4->5
        l1.next = l2;
        l2.next = l3;
        //1->3->4
        l4.next = l5;
        l5.next = l6;
        //2->6
        l7.next = l8;
        ListNode[] nodeArr = {l1, l4, l7};
        ListNode listNode = mergeKLists2(nodeArr);

        //System.out.println(trap6(a));

        //System.out.println(longestConsecutive(a));

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
