package com.example.biorreactor.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Calibration {

    // Atributos
    private final StringProperty probeName; // (pH, DO)
    private final DoubleProperty currentValue;
    private final DoubleProperty rawValue;
    private final DoubleProperty spanValue;
    private final DoubleProperty zeroValue;

    // All args constructor
    public Calibration(StringProperty probeName) {
        this.probeName = probeName;
        this.currentValue = new SimpleDoubleProperty(0);
        this.rawValue = new SimpleDoubleProperty(0);
        this.spanValue = new SimpleDoubleProperty(0);
        this.zeroValue = new SimpleDoubleProperty(0);
    }

    // Getters and Setters

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

    public String getProbeName() {
        return probeName.get();
    }

    public StringProperty probeNameProperty() {
        return probeName;
    }

    public void setProbeName(String probeName) {
        this.probeName.set(probeName);
    }

    public double getSpanValue() {
        return spanValue.get();
    }

    public DoubleProperty spanValueProperty() {
        return spanValue;
    }

    public void setSpanValue(double spanValue) {
        this.spanValue.set(spanValue);
    }

    public double getZeroValue() {
        return zeroValue.get();
    }

    public DoubleProperty zeroValueProperty() {
        return zeroValue;
    }

    public void setZeroValue(double zeroValue) {
        this.zeroValue.set(zeroValue);
    }

    public void printToConsole() {
        System.out.println("Probe Name: " + probeName.get());
        System.out.println("Current Value: " + currentValue.get());
        System.out.println("Raw Value: " + rawValue.get());
    }
}
