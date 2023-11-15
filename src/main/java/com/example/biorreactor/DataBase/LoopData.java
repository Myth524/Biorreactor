package com.example.biorreactor.DataBase;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class LoopData {

    private final long timestampMinutes;
    private final double processValue;

    public LoopData(LocalDateTime timestamp, double processValue) {
        this.timestampMinutes = ChronoUnit.MINUTES.between(LocalDateTime.MIN, timestamp);
        this.processValue = processValue;
    }

    public long timestampMinutes() {
        return timestampMinutes;
    }

    public double getProcessValue() {
        return processValue;
    }
}