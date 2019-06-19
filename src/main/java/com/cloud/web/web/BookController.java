package com.cloud.web.web;

import com.cloud.commons.constants.Constants;
import com.cloud.web.myannotation.LocalLock;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuHeYong
 * @create: 2019-06-19
 * @description:
 **/
@RestController
public class BookController {

    @LocalLock(key = "book:arg[0]")
    @GetMapping(value = Constants.CLOUD + "/book")
    public String query(@RequestParam String token) throws Exception {
        try {
            return "success = " + token;
        } catch (Exception e) {
            throw new Exception("occur error");
        }
    }

}
