package com.cloud.web.algorithm.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * @author: LiuHeYong
 * @create: 2020-04-09
 * @description: 字符串部分
 */
public class algorithmPracticeString {

    /**
     * @Date: 2020-04-09
     * @Description: 反转字符串
     */
    public static void reverseString(char[] s) {
        if (s == null || s.length == 0) {
            System.out.println("数组为空");
            return;
        }
        int n = s.length;
        String str = "";
        for (int i = n - 1; i >= 0; i--) {
            str += "\"" + s[i] + "\",";
        }
        System.out.println("[" + str.substring(0, str.lastIndexOf(",")) + "]");
    }

    /**
     * @Date: 2020-04-09
     * @Description: 反转带符号的Integer范围内的整数
     */
    public static int reverse(int x) {
        //存放结果
        int rev = 0;
        while (x != 0) {
            //每次取最后一位数
            int temp = x % 10;
            //每次取完最后一位后将其去掉
            x /= 10;
            //溢出零界点判断 范围[-2^31 , 2^31 -1] 即 [-2147483648，2147483647]
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && temp > 7))
                return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && temp < -8))
                return 0;
            rev = rev * 10 + temp;
        }
        return rev;
    }

    /**
     * @Date: 2020-04-14
     * @Param:
     * @return:
     * @Description: 字符串中的第一个唯一字符
     */
    public static int firstUniqChar01(String s) {
        if (s == null || s.equals("")) {
            return -1;
        }
        if (s.length() == 1) {
            return 0;
        }
        HashSet<Object> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (i == s.length() - 1 && !set.contains(s.charAt(i))) {
                    return i;
                }
                if (i == j) {
                    continue;
                }
                if (s.charAt(i) == s.charAt(j)) {
                    set.add(s.charAt(i));
                    break;
                }
                if (j == s.length() - 1) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * @Date: 2020-04-14
     * @Param:
     * @return:
     * @Description: 字符串中的第一个唯一字符
     */
    public static int firstUniqChar02(String s) {
        if (s == null || s.equals("")) {
            return -1;
        }
        if (s.length() == 1) {
            return 0;
        }
        TreeMap<Object, Object> treeMap = new TreeMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (treeMap.get(s.charAt(i)) != null) {
                int count = (int) treeMap.get(s.charAt(i));
                count++;
                treeMap.put(s.charAt(i), count);
            } else {
                treeMap.put(s.charAt(i), 1);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if ((int) treeMap.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @Date: 2020-04-14
     * @Param:
     * @return:
     * @Description: 字符串中的第一个唯一字符
     */
    public int firstUniqChar03(String s) {
        int n = s.length();
        for (int i = 'a'; i <= 'z'; i++) {
            int start = s.indexOf(i);
            int end = s.lastIndexOf(i);
            if (start == end && start != -1) {
                n = Math.min(start, n);
            }
        }
        if (n == s.length()) {
            return -1;
        } else {
            return n;
        }
    }

    /**
     * @Date: 2020-04-14
     * @Param:
     * @return:
     * @Description: 字符串中的第一个唯一字符
     */
    public static int firstUniqChar04(String s) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int start = s.indexOf(s.charAt(i));
            int end = s.lastIndexOf(s.charAt(i));
            if (start == end) {
                n = Math.min(start, n);
            }
        }
        if (n == s.length()) {
            return -1;
        } else {
            return n;
        }
    }

    /**
     * @Date: 2020-04-14
     * @Param:
     * @return:
     * @Description: 有效的字母异位词
     */
    public static boolean isAnagram01(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char a[] = s.toCharArray();
        char b[] = t.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
        if (Arrays.equals(a, b)) {
            return true;
        }
        return false;
    }

    /**
     * @Date: 2020-04-14
     * @Param:
     * @return:
     * @Description: 有效的字母异位词
     */
    public static boolean isAnagram02(String s, String t) {
        if (s == null || t == null || s.length() != t.length())
            return false;
        int[] map = new int[26];
        for (char c : s.toCharArray()) {
            map[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            map[c - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (map[i] != 0)
                return false;
        }
        return true;
    }

    /**
     * @Date: 2020-04-14
     * @Param:
     * @return:
     * @Description: 验证回文字符串
     */
    public static boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        if (s.equals("")) {
            return true;
        }
        int n = s.length();
        int a = 0;
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!isNumeric(s.charAt(i)) && !(isUpperCase(s.charAt(i))) && !isLowerCase(s.charAt(i))) {
                continue;
            }
            list.add(s.charAt(i));
        }
        int l = list.size();
        if ((l & 1) == 1) {
            for (int i = 0; i <= l / 2; i++) {
                if (isUpperCase(list.get(i))) {
                    a ^= list.get(i) + 32;
                } else {
                    a ^= list.get(i);
                }
                if (i != l / 2) {
                    if (isUpperCase(list.get(l - i - 1))) {
                        a ^= list.get(l - i - 1) + 32;
                    } else {
                        a ^= list.get(l - i - 1);
                    }
                    if (a != 0) {
                        return false;
                    }
                }
            }
            char m = upperCaseToLowerCase((char) a);
            char p = upperCaseToLowerCase(list.get(list.size() / 2));
            if (m == p) {
                return true;
            } else {
                return false;
            }

        }
        if ((l & 1) == 0) {
            for (int i = 0; i < l / 2; i++) {
                if (isUpperCase(list.get(i))) {
                    a ^= list.get(i) + 32;
                } else {
                    a ^= list.get(i);
                }
                if (isUpperCase(list.get(l - i - 1))) {
                    a ^= list.get(l - i - 1) + 32;
                } else {
                    a ^= list.get(l - i - 1);
                }
                if (a != 0) {
                    return false;
                }
            }
            if (a == 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /*
     * 大写转小写
     */
    public static char upperCaseToLowerCase(char c) {
        if (isLowerCase(c)) {
            return c;
        } else {
            return (char) (c + 32);
        }
    }

    /*
     * 是否是大写
     */
    public static boolean isUpperCase(char c) {
        return c >= 65 && c <= 90;
    }

    /*
     * 是否是小写
     */
    public static boolean isLowerCase(char c) {
        return c >= 97 && c <= 122;
    }

    /*
     * 是否是数字
     */
    public static boolean isNumeric(char c) {
        return c >= 48 && c <= 57;
    }

    public boolean isWord(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    public boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }

    /**
     * @Date: 2020-04-14
     * @Param:
     * @return:
     * @Description: 验证回文字符串
     */
    public boolean isPalindrome02(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            char lCh = s.charAt(left);
            char rCh = s.charAt(right);
            if (!(isWord(lCh) || isNum(lCh))) {
                left++;
                continue;
            } else if (!(isWord(rCh) || isNum(rCh))) {
                right--;
                continue;
            }
            if (lCh == rCh) {
                left++;
                right--;
            } else if (isWord(rCh) && isWord(lCh)) {
                return rCh - lCh == 'a' - 'A' || rCh - lCh == 'A' - 'a';
            } else {
                return false;
            }
        }
        return true;
    }

    static char[] s = {'H', '2', 'n', 'e', 'a', 'h'};

    public static void main(String[] args) {

        //System.out.println(isPalindrome("!043X jqj X043!"));

        //System.out.println(isAnagram02("hhbyad", "hhbygd"));

        //System.out.println(firstUniqChar04("aadadaad"));

        //System.out.println(firstUniqChar02("aadadaad"));

        //System.out.println(firstUniqChar01("aadadaad"));

        //reverseString(s);

    }

}
