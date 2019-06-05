package com.cloud.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.RequestContextListener;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@EnableEurekaServer
@SpringBootApplication
public class ZCloudWebApplication implements CommandLineRunner {

    public static final Logger logger = LoggerFactory.getLogger(ZCloudWebApplication.class);

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ZCloudWebApplication.class);
        //禁止命令行设置环境参数
        springApplication.setAddCommandLineProperties(false);
        springApplication.run(args);
    }

    /**
     * @date: 2019/5/28
     * @param: [strings]
     * @return: void
     * @description: 这个方法不需要手动调用，启动以后这个方法会被自动执行并存在于Spring容器中
     */
    @Override
    public void run(String... strings) {
        try {
            logger.info("============随cloud启动而执行============");
        } catch (Exception e) {
            logger.error("启动异常", e);
            e.printStackTrace();
        }
    }

    /**
     * @date: 2019/5/28
     * @description: 在主类中定义⼀个线程池
     */
    @EnableAsync
    @Configuration
    class TaskPoolConfig {
        @Bean("taskExecutor")
        public Executor taskExecutor() {
            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(10);
            executor.setMaxPoolSize(20);
            executor.setQueueCapacity(200);
            executor.setKeepAliveSeconds(60);
            executor.setThreadNamePrefix("taskExecutor-");
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            return executor;
        }
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

}
