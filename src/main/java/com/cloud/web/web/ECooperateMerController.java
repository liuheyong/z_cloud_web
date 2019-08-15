package com.cloud.web.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cloud.commons.constants.Constants;
import com.cloud.commons.dto.ECooperateMer;
import com.cloud.commons.response.QueryECooperateMerResponse;
import com.cloud.commons.response.Result;
import com.cloud.commons.service.ECooperateMerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: LiuHeYong
 * @create: 2019-05-22
 * @description: ECooperateMerController
 **/
@Controller
public class ECooperateMerController extends DefaultController {

    public static final Logger logger = LoggerFactory.getLogger(ECooperateMerController.class);

    @Reference(check = false, version = "1.0.0", timeout = 60000)
    private ECooperateMerService eCooperateMerService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @date: 2019/5/24
     * @param: [eCooperateMer]
     * @return: Result
     * @description: 详情
     */
    @ResponseBody
    @RequestMapping(value = Constants.CLOUD + "/queryECooperateMerInfo")
    public Result queryECooperateMerInfo(ECooperateMer eCooperateMer) {
        Result result = new Result();
        Map<String, Object> model = new HashMap<String, Object>(4);
        try {
            Optional<ECooperateMer> optDto = Optional.ofNullable(eCooperateMerService.queryECooperateMerInfo(eCooperateMer));
            if (optDto.isPresent()) {
                model.put("eCooperateMer", optDto.get());
                result.setResultData(model);
                result.setResultCode(Constants.RESULT_SUCCESS);
                return result;
            } else {
                throw new Exception("该eCooperateMer不存在");
            }
        } catch (Exception e) {
            logger.error("系统异常", e);
            result.setResultCode(Constants.RESULT_FAIL);
            result.setResultMessage("系统异常");
        }
        return result;
    }

    /**
     * @date: 2019/5/27
     * @param: [eCooperateMer]
     * @return: com.boot.com.alibabacloud.commons.response.Result
     * @description: 创建线程查询列表
     */
    @ResponseBody
    @RequestMapping(value = Constants.CLOUD + "/queryECooperateMerListPage")
    public Result queryECooperateMerListPage(ECooperateMer eCooperateMer) throws InterruptedException {
//        Thread currentThread = Thread.currentThread();
//        synchronized (currentThread) {
//            currentThread.wait(2);
//        }
        Result result = new Result();
        Map<String, Object> model = new HashMap<String, Object>(4);
        try {
            //创建线程执行任务
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        eCooperateMerService.queryECooperateMerListPage(eCooperateMer);
                    } catch (Exception e) {
                        logger.error("系统异常", e);
                        return;
                    }
                }
            };
            //创建线程池
            ExecutorService executorService = Executors.newFixedThreadPool(25);
            for (int i = 0; i < Constants.NUMBER_100; i++) {
                executorService.submit(runnable);
            }
            QueryECooperateMerResponse response = eCooperateMerService.queryECooperateMerListPage(eCooperateMer);
            model.put("eCooperateMerList", response.geteCooperateMerList());
            result.setResultCode(Constants.RESULT_SUCCESS);
            result.setResultData(model);
        } catch (Exception e) {
            logger.error("系统异常", e);
            result.setResultCode(Constants.RESULT_FAIL);
            result.setResultMessage("系统异常");
        }
        return result;

    }

    @RequestMapping("/testError")
    public String testError() throws Exception {
        throw new Exception("occur error");
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
