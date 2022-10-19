package com.example.sensorsimulator;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SensorEvent {
  private int sensorId;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime dateTime;
  private double ozone;
  private double nitrogenDioxide;
  private double carbonMonoxide;
  private double sulfurDioxide;
  private double pm10;
  private double pm25;
}
