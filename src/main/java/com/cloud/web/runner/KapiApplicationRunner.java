package com.cloud.web.runner;

import com.cloud.web.web.BookController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author: heyongliu
 * @date: 2021/11/18
 * @description: SpringBoot内置Tomcat线程池监控
 */
@Component
public class KapiApplicationRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    WebServerApplicationContext webServerApplicationContext;

    /**
     * @param args incoming application arguments
     */
    @Override
    public void run(ApplicationArguments args) {
        ////获取Spring容器线程池Map
        //Map<String, ExecutorService> executorMap = SpringUtil.getBeansOfType(ExecutorService.class);
        //Map<String, InnerExecutorBean> resultMap = new LinkedHashMap<>();
        //executorMap.forEach((key, value) -> {
        //    //获取JUC线程池
        //    if (value instanceof java.util.concurrent.ThreadPoolExecutor) {
        //        java.util.concurrent.ThreadPoolExecutor jucExecutor = (java.util.concurrent.ThreadPoolExecutor) value;
        //        InnerExecutorBean executorBean = new InnerExecutorBean();
        //        executorBean.setCorePoolSize(String.valueOf(jucExecutor.getCorePoolSize()));
        //        executorBean.setMaximumPoolSize(String.valueOf(jucExecutor.getMaximumPoolSize()));
        //        executorBean.setActiveCount(String.valueOf(jucExecutor.getActiveCount()));
        //        executorBean.setPoolSize(String.valueOf(jucExecutor.getPoolSize()));
        //        executorBean.setQueueSize(String.valueOf(jucExecutor.getQueue().size()));
        //        resultMap.put(key, executorBean);
        //    } else if (value instanceof org.apache.tomcat.util.threads.ThreadPoolExecutor) {
        //        org.apache.tomcat.util.threads.ThreadPoolExecutor tomcatExecutor = (org.apache.tomcat.util.threads.ThreadPoolExecutor) value;
        //        InnerExecutorBean executorBean = new InnerExecutorBean();
        //        executorBean.setCorePoolSize(String.valueOf(tomcatExecutor.getCorePoolSize()));
        //        executorBean.setMaximumPoolSize(String.valueOf(tomcatExecutor.getMaximumPoolSize()));
        //        executorBean.setActiveCount(String.valueOf(tomcatExecutor.getActiveCount()));
        //        executorBean.setPoolSize(String.valueOf(tomcatExecutor.getPoolSize()));
        //        executorBean.setQueueSize(String.valueOf(tomcatExecutor.getQueue().size()));
        //        resultMap.put(key, executorBean);
        //    }
        //});
        //
        ////获取webServer线程池
        //ThreadPoolExecutor executor = (ThreadPoolExecutor) ((TomcatWebServer) webServerApplicationContext.getWebServer())
        //        .getTomcat()
        //        .getConnector()
        //        .getProtocolHandler()
        //        .getExecutor();
        //Map<String, String> returnMap = new LinkedHashMap<>();
        //returnMap.put("核心线程数", String.valueOf(executor.getCorePoolSize()));
        //returnMap.put("最大线程数", String.valueOf(executor.getMaximumPoolSize()));
        //returnMap.put("活跃线程数", String.valueOf(executor.getActiveCount()));
        //returnMap.put("池中当前线程数", String.valueOf(executor.getPoolSize()));
        //returnMap.put("历史最大线程数", String.valueOf(executor.getLargestPoolSize()));
        //returnMap.put("线程允许空闲时间/s", String.valueOf(executor.getKeepAliveTime(TimeUnit.SECONDS)));
        //returnMap.put("核心线程数是否允许被回收", String.valueOf(executor.allowsCoreThreadTimeOut()));
        //returnMap.put("提交任务总数", String.valueOf(executor.getSubmittedCount()));
        //returnMap.put("历史执行任务的总数(近似值)", String.valueOf(executor.getTaskCount()));
        //returnMap.put("历史完成任务的总数(近似值)", String.valueOf(executor.getCompletedTaskCount()));
        //returnMap.put("工作队列任务数量", String.valueOf(executor.getQueue().size()));
        //returnMap.put("拒绝策略", executor.getRejectedExecutionHandler().getClass().getSimpleName());
        //logger.info("tomcat线程池指标信息: {}", JSON.toJSONString(returnMap));
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
