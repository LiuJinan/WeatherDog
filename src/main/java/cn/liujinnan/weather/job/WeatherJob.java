package cn.liujinnan.weather.job;

import cn.liujinnan.weather.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class WeatherJob {

    @Autowired
    private WeatherService weatherService;

    private static final Logger logger = LoggerFactory.getLogger(WeatherJob.class);

    /**
     *  早上执行任务， 通知下雨
     */
    @Scheduled(cron = "0 0 7 * * ?")
    public void test() {
        logger.info("开始执行任务");
        weatherService.notifyOfRain();
    }
}
