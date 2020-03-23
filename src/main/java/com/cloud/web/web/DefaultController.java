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

}
