package com.cloud.web.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;

/**
 * @author: LiuHeYong
 * @create: 2019-04-18
 * @exception:
 * @description: defaultcontroller
 **/
@Controller
public class DefaultController implements Cloneable, Serializable {

    public static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

    private static final long serialVersionUID = 1L;

    @RequestMapping("/test")
    public String test() throws Exception {
        return "hello";
    }

}
