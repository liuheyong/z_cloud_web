package com.cloud.web.web;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * @author: liuheyong
 * @create: 2019-08-31
 * @description:
 */
@Controller("testController")
//@Order(2)
public class TestController extends DefaultController implements Ordered {

    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private Executor asynServiceExecutor;

    public TestController() {
        System.out.println("TestController");
    }

    /**
     * @Date: 2019-08-31
     * @Param: []
     * @return: java.lang.String
     * @Description: 设置访问首页
     */
    @RequestMapping("")
    public String test() {
        return "redirect:/cloud/queryECooperateMerListPage";
    }

    /**
     * @Date: 2019-08-31
     * @Param: []
     * @return: java.lang.String
     * @Description: 跨域测试
     */
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

    /**
     * @Date: 2019-08-31
     * @Param: []
     * @return: java.lang.String
     * @Description: redis测试
     */
    @RequestMapping("/testRedis")
    @ResponseBody
    public String testRedis() {
        //redis 设置key
        //RedisSerializer redisSerializer = new StringRedisSerializer();
        //redisTemplate.setKeySerializer(redisSerializer);
        return (String) redisTemplate.opsForValue().get("test");
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }

    /**
     * 探活接口
     *
     * @author: heyongliu
     * @date: 2022/3/23
     */
    @GetMapping("/test2")
    public void test2() {
        asynServiceExecutor.execute(() -> logger.info("测试线程池"));
    }

    /**
     * 内部接口，仅供研发人员手动调用使用
     *
     * @author: heyongliu
     * @date: 2022/3/23
     */
    @GetMapping("/dtp")
    public void dtp(@RequestParam("dtp") String dtp) {
        if (!"kapi-dtp".equals(dtp)) {
            return;
        }
        //获取JUC线程池
        Map<String, Executor> executorMap = SpringUtil.getBeansOfType(Executor.class);
        ////获取webServer线程池
        //ThreadPoolExecutor executor = (ThreadPoolExecutor) ((TomcatWebServer) webServerApplicationContext.getWebServer())
        //        .getTomcat()
        //        .getConnector()
        //        .getProtocolHandler()
        //        .getExecutor();
        ////按照插入顺序排序
        //Map<String, Object> returnMap = new LinkedHashMap<>();
        //returnMap.put("核心线程数", executor.getCorePoolSize());
        //returnMap.put("最大线程数", executor.getMaximumPoolSize());
        //returnMap.put("活跃线程数", executor.getActiveCount());
        //returnMap.put("池中当前线程数", executor.getPoolSize());
        //returnMap.put("历史最大线程数", executor.getLargestPoolSize());
        //returnMap.put("线程允许空闲时间/s", executor.getKeepAliveTime(TimeUnit.SECONDS));
        //returnMap.put("核心线程数是否允许被回收", executor.allowsCoreThreadTimeOut());
        ////returnMap.put("提交任务总数", executor.getSubmittedCount());
        //returnMap.put("历史执行任务的总数(近似值)", executor.getTaskCount());
        //returnMap.put("历史完成任务的总数(近似值)", executor.getCompletedTaskCount());
        //returnMap.put("工作队列任务数量", executor.getQueue().size());
        //returnMap.put("拒绝策略", executor.getRejectedExecutionHandler().getClass().getSimpleName());
        //LoggerUtil.logger().info("Tomcat线程池监控信息: {}", returnMap);
    }
}
