package com.example.sensorsimulator;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

  @Bean
  public ThreadPoolTaskScheduler sensorScheduler(){
    ThreadPoolTaskScheduler sensorScheduler = new ThreadPoolTaskScheduler();
    sensorScheduler.setPoolSize(5);
    sensorScheduler.setThreadNamePrefix("sensorScheduler-");
    sensorScheduler.setWaitForTasksToCompleteOnShutdown(false);
    return sensorScheduler;
  }
}
