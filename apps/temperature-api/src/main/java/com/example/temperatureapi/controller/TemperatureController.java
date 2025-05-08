package com.example.temperatureapi.controller;

import com.example.temperatureapi.model.Sensor;
import com.example.temperatureapi.model.TemperatureResponse;
import com.example.temperatureapi.repository.SensorRepository;
import com.example.temperatureapi.utils.TemperatureGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/temperature")
public class TemperatureController {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'");

    @Autowired
    private SensorRepository sensorRepository;

    @GetMapping
    public ResponseEntity<TemperatureResponse> getCurrentTemperatureInLocation(@RequestParam String location) {
        if (location == null || location.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return handleQueryResult(sensorRepository.findFirstByLocation(location));
        }
    }

    @GetMapping("/{sensorId}")
    public ResponseEntity<TemperatureResponse> getCurrentTemperatureBySensorId(@PathVariable Integer sensorId) {
        return handleQueryResult(sensorRepository.findById(sensorId));
    }

    private ResponseEntity<TemperatureResponse> handleQueryResult(Optional<Sensor> optionalSensor) {
        if (optionalSensor.isPresent()) {
            Sensor sensor = optionalSensor.get();
            sensor.setValue(TemperatureGenerator.getTemperature());
            return ResponseEntity.ok(transform(sensor));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private TemperatureResponse transform(Sensor sensor) {
        return new TemperatureResponse(sensor.getValue(), sensor.getUnit(),
                LocalDateTime.now().format(FORMATTER), sensor.getLocation(),
                sensor.getStatus(), sensor.getId().toString(),
                sensor.getType(), sensor.getName());
    }
}
