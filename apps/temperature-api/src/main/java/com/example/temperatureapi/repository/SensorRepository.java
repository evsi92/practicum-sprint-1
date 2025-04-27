package com.example.temperatureapi.repository;

import com.example.temperatureapi.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {

    @Override
    Optional<Sensor> findById(Integer id);

    Optional<Sensor> findFirstByLocation(String location);
}
