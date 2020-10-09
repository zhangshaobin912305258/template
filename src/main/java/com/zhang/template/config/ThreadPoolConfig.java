package com.zhang.template.config;

import com.zhang.template.util.Threads;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class ThreadPoolConfig {

    @Bean(name = "threadPoolExecutor")
    public ThreadPoolExecutor threadPoolExecutor(ThreadPoolProperties properties) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(properties.getCorePoolSize(),
                properties.getMaxPoolSize(),properties.getKeepAliveSeconds(),
                TimeUnit.SECONDS,new LinkedBlockingDeque<>(properties.getQueueCapacity()), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        return threadPoolExecutor;
    }

    /**
     * 执行周期性或定时任务
     */
    @Bean(name = "scheduledExecutorService")
    protected ScheduledExecutorService scheduledExecutorService(ThreadPoolProperties properties) {
        return new ScheduledThreadPoolExecutor(properties.getCorePoolSize(),
                new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build()) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                Threads.printException(r, t);
            }
        };
    }
}
