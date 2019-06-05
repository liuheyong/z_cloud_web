package com.cloud.web.web;

import com.cloud.commons.dto.Student;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

/**
 * @author: LiuHeYong
 * @create: 2019-04-18
 * @exception:
 * @description: defaultcontroller
 **/
public class DefaultController implements Cloneable, Serializable {

    public static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

    private static final long serialVersionUID = 1L;

    public static Student student01 = new Student("liu", 91);
    public static Student student02 = new Student("wang", 92);
    public static Student student03 = new Student("huang", 93);
    public static Student student04 = new Student("han", 94);
    public static Student student05 = new Student("han", 94);
    public static List<Student> studentList = Lists.newArrayList(student01, student02, student03, student04, student05);

}
