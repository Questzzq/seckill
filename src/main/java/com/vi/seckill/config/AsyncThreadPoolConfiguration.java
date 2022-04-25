package com.vi.seckill.config;

import com.alibaba.ttl.threadpool.TtlExecutors;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Eric Tseng
 * @description AsyncThreadPoolConfiguration
 * @since 2022/4/24 23:42
 */
@Configuration
public class AsyncThreadPoolConfiguration implements AsyncConfigurer {
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
    @Value("${log.threadNamePrefix: AsyncLogT-}")
    private String threadNamePrefix;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(corePoolSize);
        threadPool.setMaxPoolSize(maxPoolSize);
        threadPool.setKeepAliveSeconds(keepAliveSeconds);
        threadPool.setQueueCapacity(queueCapacity);
        threadPool.setThreadNamePrefix(threadNamePrefix);
        // 线程池对拒绝任务的拒绝策略
        // CallerRunsPolicy: 由调用线程（提交任务的线程）处理该任务
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPool.initialize();
        // 重点：使用 TTL 提供的 TtlExecutors
        return TtlExecutors.getTtlExecutor(threadPool);
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }
}
