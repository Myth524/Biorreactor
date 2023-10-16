package com.example.biorreactor.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Calibration {

    // Atributos
    private StringProperty loopName; // (pH, DO)
    private DoubleProperty currentValue;
    private DoubleProperty rawValue;

    // All args constructor
    public Calibration(StringProperty loopName) {
        this.loopName = loopName;
        this.currentValue = (new SimpleDoubleProperty(0));
        this.rawValue = (new SimpleDoubleProperty(0));
    }

    // Getters and Setters
    public String getLoopName() {
        return loopName.get();
    }

    public StringProperty loopNameProperty() {
        return loopName;
    }

    public void setLoopName(String loopName) {
        this.loopName.set(loopName);
    }

    public double getCurrentValue() {
        return currentValue.get();
    }

    public DoubleProperty currentValueProperty() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue.set(currentValue);
    }

    public double getRawValue() {
        return rawValue.get();
    }

    public DoubleProperty rawValueProperty() {
        return rawValue;
    }

    public void setRawValue(double rawValue) {
        this.rawValue.set(rawValue);
    }

    public void printToConsole() {
        System.out.println("Loop Name: " + loopName.get());
        System.out.println("Current Value: " + currentValue.get());
        System.out.println("Raw Value: " + rawValue.get());
    }
}
