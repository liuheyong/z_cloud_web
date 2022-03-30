package com.cloud.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: heyongliu
 * @date: 2021/7/14 16:15
 * @description: 自定义线程池
 */
@Configuration
public class ThreadExecutorConfig {

    @Bean
    public static Executor asynServiceExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(8);
        //配置最大线程数
        executor.setMaxPoolSize(30);
        //配置队列大小
        executor.setQueueCapacity(1000);
        // 设置线程活跃时间(秒)
        executor.setKeepAliveSeconds(60);
        //设置默认线程名称前缀
        executor.setThreadNamePrefix("asyn-thread-");
        //设置线程池组名
        executor.setThreadGroupName("asyn-thread-group");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }
}
