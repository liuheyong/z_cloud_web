package com.cloud.web.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/test")
    public String test() {
        return "hello";
    }

    @CrossOrigin("http://localhost:8118")
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @ResponseBody
    @RequestMapping("/json")
    public String json() {
        return "json";
    }

    @RequestMapping("/testRedis")
    @ResponseBody
    public String testRedis() {
        //redis 设置key
        //RedisSerializer redisSerializer = new StringRedisSerializer();
        //redisTemplate.setKeySerializer(redisSerializer);
        return (String) redisTemplate.opsForValue().get("test");
    }

}
