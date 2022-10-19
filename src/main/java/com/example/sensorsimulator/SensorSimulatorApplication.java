package com.example.sensorsimulator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;

@SpringBootApplication
@Slf4j
public class SensorSimulatorApplication {

	public static final int NUMBER_OF_SENSORS = 2;

	public static final String endPoint = "http://localhost:9191/measurements";

	@Autowired
	private ThreadPoolTaskScheduler sensorScheduler;

	@Autowired
	private RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SensorSimulatorApplication.class, args);
	}

	@PostConstruct
	public void scheduleSensors(){
		IntStream.range(1, NUMBER_OF_SENSORS + 1)
				.mapToObj(i -> new Sensor(i, restTemplate, endPoint))
				.forEach(sensor -> sensorScheduler.scheduleAtFixedRate(sensor, Instant.now(), Duration.ofSeconds(2l)));
	}
}
