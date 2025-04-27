package com.example.temperatureapi.controller;

import com.example.temperatureapi.model.Sensor;
import com.example.temperatureapi.repository.SensorRepository;
import com.example.temperatureapi.utils.TemperatureGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/temperature")
public class TemperatureController {

    @Autowired
    private SensorRepository sensorRepository;

    @GetMapping
    public ResponseEntity<Sensor> getCurrentTemperatureInLocation(@RequestParam String location) {
        if (location == null || location.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return handleQueryResult(sensorRepository.findFirstByLocation(location));
        }
    }

    @GetMapping("/{sensorId}")
    public ResponseEntity<Sensor> getCurrentTemperatureBySensorId(@PathVariable Integer sensorId) {
        return handleQueryResult(sensorRepository.findById(sensorId));
    }

    private ResponseEntity<Sensor> handleQueryResult(Optional<Sensor> optionalSensor) {
        if (optionalSensor.isPresent()) {
            Sensor sensor = optionalSensor.get();
            sensor.setValue(TemperatureGenerator.getTemperature());
            return ResponseEntity.ok(sensor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
