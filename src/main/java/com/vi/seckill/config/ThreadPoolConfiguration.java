package com.vi.seckill.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中。
 * 当队列满了，就会继续创建线程，当线程数量大于等于maxPoolSize后，开始使用拒绝策略。
 * @author Eric Tseng
 * @description ThreadPoolConfiguration
 * @since 2022/3/6 15:34
 */
@Configuration
@EnableAsync
public class ThreadPoolConfiguration {
    /**
     * 核心线程数（默认线程数）
     */
    @Value("${log.corePoolSize: 5}")
    private int corePoolSize;

    /**
     * 最大线程数
     */
    @Value("${log.maxPoolSize: 10}")
    private int maxPoolSize;

    /**
     * 允许线程空闲时间（单位：默认为秒）
     */
    @Value("${log.keepAliveSeconds: 60}")
    private int keepAliveSeconds;

    /**
     * 缓冲队列容量
     */
    @Value("${log.queueCapacity: 50}")
    private int queueCapacity;

    /**
     * 线程池名前缀
     */
    @Value("${log.threadNamePrefix: logT-}")
    private String threadNamePrefix;

    @Bean("logThread")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix);
        // 线程池对拒绝任务的拒绝策略
        // CallerRunsPolicy: 由调用线程（提交任务的线程）处理该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
        executor.initialize();
        return executor;
    }

}
