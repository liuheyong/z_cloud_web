package com.cloud.web.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * @author: LiuHeYong
 * @create: 2019-04-18
 * @exception:
 * @description: defaultcontroller
 **/
@RestController
public class DefaultController implements Cloneable, Serializable {

    public static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

    private static final long serialVersionUID = 1L;

}
