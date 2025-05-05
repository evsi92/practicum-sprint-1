package com.example.temperatureapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TemperatureResponse {

    @JsonProperty("value")
    Float value;

    @JsonProperty("unit")
    String unit;

    @JsonProperty("timestamp")
    String timestamp;

    @JsonProperty("location")
    String location;

    @JsonProperty("status")
    String status;

    @JsonProperty("sensor_id")
    Integer id;

    @JsonProperty("sensor_type")
    String type;

    @JsonProperty("description")
    String name;

    public TemperatureResponse(Float value, String unit, String timestamp, String location, String status, Integer id, String type, String name) {
        this.value = value;
        this.unit = unit;
        this.timestamp = timestamp;
        this.location = location;
        this.status = status;
        this.id = id;
        this.type = type;
        this.name = name;
    }
}
