package com.example.sensorsimulator;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
public class Sensor implements Runnable {
  private final int id;
  private RestTemplate restTemplate;

  private String endPoint;

  public Sensor(int id, RestTemplate restTemplate, String endPoint) {
    this.id = id;
    this.restTemplate = restTemplate;
    this.endPoint = endPoint;
  }

  @Override
  public void run() {
    SensorEvent event = getEvent();
    log.info("Sensor {} generated event {}", id, event);

    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<String> request = new HttpEntity<>(JsonUtil.toJson(event), headers);
      ResponseEntity<?> response = restTemplate.postForEntity(endPoint, request, String.class);
      log.info("Sensor {} generated event {} and response is {}", id, event, response);
    } catch (JsonProcessingException e) {
      log.error("Failed to process event: {}", event, e);
    }
  }

  private SensorEvent getEvent(){
    return SensorEvent.builder()
        .sensorId(id)
        .dateTime(LocalDateTime.now())
        .ozone(new Random().nextDouble())
        .nitrogenDioxide(new Random().nextDouble())
        .carbonMonoxide(new Random().nextDouble())
        .sulfurDioxide(new Random().nextDouble())
        .pm10(new Random().nextDouble())
        .pm25(new Random().nextDouble())
        .build();
  }
}
