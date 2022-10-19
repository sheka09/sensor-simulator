package com.example.sensorsimulator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtil {
  private static ObjectMapper mapper = new ObjectMapper()
      .registerModule(new JavaTimeModule())
      .setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());

  public static String toJson(Object o) throws JsonProcessingException {
    return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
  }
}
