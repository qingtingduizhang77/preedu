package com.tware.common.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * 定时任务
 */
@Component
@Configuration
@EnableScheduling
public class ScheduledControl {

    private static final Logger log = LoggerFactory.getLogger(ScheduledControl.class);


    /**
     * 定时任务
     */
    @Scheduled(cron = "0 0 0 1 * ?")
    public void test(){
        log.info("start ");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        stopWatch.stop();
        log.info("end ");
        log.info("消耗时间：" + stopWatch.getTotalTimeMillis() + "ms");
    }

}
