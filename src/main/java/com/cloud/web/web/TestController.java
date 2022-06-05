package com.cloud.web.web;

import cn.hutool.extra.spring.SpringUtil;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.openjdk.jol.info.ClassLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: liuheyong
 * @create: 2019-08-31
 * @description:
 */
@Controller("testController")
//@Order(2)
public class TestController extends DefaultController implements Ordered {

    private static final AtomicLong counter = new AtomicLong(0L);

    @Resource
    private RestTemplate restTemplate;
    @Autowired
    private RedisTemplate<?, ?> redisTemplate;
    @Resource
    private Executor asynServiceExecutor;
    @Autowired
    private WebServerApplicationContext webServerApplicationContext;

    public TestController() {
        System.out.println("TestController");
    }

    public static void main(String[] args) {
        //打印出对象的内存布局
        System.out.println(ClassLayout.parseInstance(new TestController()).toPrintable());
    }

    /**
     * @Date: 2019-08-31
     * @Param: []
     * @return: java.lang.String
     * @Description: 设置访问首页
     */
    @ResponseBody
    @RequestMapping("test_asyn_context")
    public String testAsynContext(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(Thread.currentThread().getName());
        AsyncContext asyncContext = request.startAsync(request, response);
        asyncContext.setTimeout(0L);
        asyncContext.start(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(12000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            asyncContext.complete();
        });
        return "testAsynContext";
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
        new Thread(() -> {
            while (true) {
                try {
                    asynServiceExecutor.execute(() -> restTemplate.getForEntity("https://blog.csdn.net/phoenix/web/home/get-user-info-list?usernames=devcloud,gc5r8w07u,weixin_38912070,Kingbase_,weixin_45449540,epubit17,yb1314111,yunqiinsight,weixin_44326589,jdcdev_,MeituanTech,alitech2017" + counter.incrementAndGet(), String.class));
                } catch (Exception e) {
                    logger.error("捕获到异常================ ", e);
                    break;
                }
            }
        }).start();
    }

    /**
     * 探活接口
     *
     * @author: heyongliu
     * @date: 2022/3/23
     */
    @GetMapping("/test3")
    public void test3() {
        new Thread(() -> {
            while (true) {
                try {
                    asynServiceExecutor.execute(() -> restTemplate.getForEntity("https://blog.csdn.net/phoenix/web/home/get-user-info-list?usernames=devcloud,gc5r8w07u,weixin_38912070,Kingbase_,weixin_45449540,epubit17,yb1314111,yunqiinsight,weixin_44326589,jdcdev_,MeituanTech,alitech2017" + counter.incrementAndGet(), String.class));
                } catch (Exception e) {
                    logger.error("捕获到异常================ ", e);
                    break;
                }
            }
        }).start();
    }

    /**
     * 内部接口，仅供研发人员必要时手动调用使用
     * 获取ThreadPoolTaskExecutor和Tomcat线程池信息
     *
     * @author: heyongliu
     * @date: 2022/3/23
     */
    @GetMapping("/dtp")
    @ResponseBody
    public Map<String, InnerExecutorBean> dtp() {
        Map<String, InnerExecutorBean> resultMap = new LinkedHashMap<>(8);
        Map<String, Executor> serviceExecutorMap = SpringUtil.getBeansOfType(Executor.class);
        serviceExecutorMap.forEach((key, value) -> {
            //获取taskExecutor线程池
            if (value instanceof ThreadPoolTaskExecutor) {
                ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) value;
                InnerExecutorBean executorBean = new InnerExecutorBean();
                executorBean.setCorePoolSize(String.valueOf(taskExecutor.getCorePoolSize()));
                executorBean.setMaximumPoolSize(String.valueOf(taskExecutor.getMaxPoolSize()));
                executorBean.setActiveCount(String.valueOf(taskExecutor.getActiveCount()));
                executorBean.setPoolSize(String.valueOf(taskExecutor.getPoolSize()));
                executorBean.setQueueSize(String.valueOf(taskExecutor.getThreadPoolExecutor().getQueue().size()));
                resultMap.put(key, executorBean);
            }
        });
        //获取webServer线程池
        ThreadPoolExecutor tomcatExecutor = (ThreadPoolExecutor) ((TomcatWebServer) webServerApplicationContext.getWebServer())
                .getTomcat()
                .getConnector()
                .getProtocolHandler()
                .getExecutor();
        InnerExecutorBean executorBean = new InnerExecutorBean();
        executorBean.setCorePoolSize(String.valueOf(tomcatExecutor.getCorePoolSize()));
        executorBean.setMaximumPoolSize(String.valueOf(tomcatExecutor.getMaximumPoolSize()));
        executorBean.setActiveCount(String.valueOf(tomcatExecutor.getActiveCount()));
        executorBean.setPoolSize(String.valueOf(tomcatExecutor.getPoolSize()));
        executorBean.setQueueSize(String.valueOf(tomcatExecutor.getQueue().size()));
        resultMap.put("tomcatExecutor", executorBean);
        return resultMap;
    }

    public static class InnerExecutorBean {

        /**
         * 核心线程数
         */
        private String corePoolSize;
        /**
         * 最大线程数
         */
        private String maximumPoolSize;
        /**
         * 活跃线程数[持有state的锁的线程]
         */
        private String activeCount;
        /**
         * 池中当前线程数
         */
        private String poolSize;
        /**
         * 工作队列任务数量
         */
        private String queueSize;

        public String getCorePoolSize() {
            return corePoolSize;
        }

        public void setCorePoolSize(String corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public String getMaximumPoolSize() {
            return maximumPoolSize;
        }

        public void setMaximumPoolSize(String maximumPoolSize) {
            this.maximumPoolSize = maximumPoolSize;
        }

        public String getActiveCount() {
            return activeCount;
        }

        public void setActiveCount(String activeCount) {
            this.activeCount = activeCount;
        }

        public String getPoolSize() {
            return poolSize;
        }

        public void setPoolSize(String poolSize) {
            this.poolSize = poolSize;
        }

        public String getQueueSize() {
            return queueSize;
        }

        public void setQueueSize(String queueSize) {
            this.queueSize = queueSize;
        }
    }
}
