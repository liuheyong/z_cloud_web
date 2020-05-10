package com.cloud.web.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @author: LiuHeYong
 * @create: 2019-04-18
 * @exception:
 * @description: defaultcontroller
 **/
public class DefaultController implements Cloneable, Serializable {

    public static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

    private static final long serialVersionUID = 1L;

    /**
     * @description: 域名提取 例：https://www.baidu.com/s?ie=UTF-8&wd=devv 提取之后返回 www.baidu.com
     */
    protected String domainUrlExtract(HttpServletRequest request) {
        StringBuffer requestURL = request.getRequestURL();
        String[] split = requestURL.toString().split("//");
        String[] split2 = split[1].split("/");
        return split2[0];
    }

    public static Boolean checkFileTypeExcel(String fileName) {
        /** 检查文件名是否为空或者是否是Excel格式的文件 */
        if (!fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
            return false;
        }
        return true;
    }

    public static int maxInTwoNum(int a, int b) {
        return a > b ? a : b;
    }

    public static int maxInThreeNum(int S_Left, int S_Right, int S_Cross_Left_Right) {
        return (S_Left > S_Right ? (S_Left > S_Cross_Left_Right ? S_Left : S_Cross_Left_Right) : (S_Right > S_Cross_Left_Right ?
                S_Right : S_Cross_Left_Right));
    }

    public static void swap(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
    }

    //交换数组a中的a[i]和a[j]
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
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
     * 链表节点
     */
    public class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int x) {
            val = x;
        }
    }

}
