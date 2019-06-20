package com.cloud.web.web;

import com.cloud.commons.constants.Constants;
import com.cloud.web.myannotation.AopTest;
import com.cloud.web.myannotation.CacheLock;
import com.cloud.web.myannotation.CacheParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: LiuHeYong
 * @create: 2019-06-19
 * @description: BookController
 **/
@RestController
public class BookController {

    public static final Logger logger = LoggerFactory.getLogger(BookController.class);

    //@LocalLock(key = "book:arg[0]")
    @GetMapping(value = Constants.CLOUD + "/books")
    @CacheLock(prefix = "books")
    public String query(@CacheParam(name = "token") @RequestParam String token) throws Exception {
        try {
            return "success = " + token;
        } catch (Exception e) {
            throw new Exception("occur error");
        }
    }

    @GetMapping(value = Constants.CLOUD + "/aopTest")
    @AopTest
    public String aopTest(String token) throws Exception {
        try {
            logger.info("==========进入aopTest方法==========");
            return token;
        } catch (Exception e) {
            throw new Exception("occur error");
        }
    }

}
