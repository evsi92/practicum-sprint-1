package com.example.temperatureapi.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class TemperatureGenerator {

    public static Float getTemperature() {
        return new BigDecimal(new Random().nextFloat(100)).setScale(2, RoundingMode.HALF_UP).floatValue();
    }
}
