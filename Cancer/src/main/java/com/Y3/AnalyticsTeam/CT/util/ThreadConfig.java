package com.Y3.AnalyticsTeam.CT.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ThreadConfig {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private static int corePoolSize = Runtime.getRuntime().availableProcessors();

    @Bean
    public ExecutorService getExecutorTools(){
        LOGGER.info("### corePoolSize->"+corePoolSize);
        ExecutorService executorService = Executors.newFixedThreadPool(corePoolSize*2);
        return  executorService;
    }
}
