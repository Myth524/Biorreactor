package com.example.biorreactor.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class PID {

    // Atributos
    private DoubleProperty proportional;
    private DoubleProperty normalizing;
    private DoubleProperty integral;
    private DoubleProperty lowLimit;
    private DoubleProperty highLimit;

    // All args constructor
    public PID() {
        this.proportional = (new SimpleDoubleProperty(0));
        this.normalizing = (new SimpleDoubleProperty(0));
        this.integral = (new SimpleDoubleProperty(0));
        this.lowLimit = (new SimpleDoubleProperty(0));
        this.highLimit = (new SimpleDoubleProperty(0));
    }

    // Getters and Setters
    public double getProportional() {
        return proportional.get();
    }

    public DoubleProperty proportionalProperty() {
        return proportional;
    }

    public void setProportional(double proportional) {
        this.proportional.set(proportional);
    }

    public double getNormalizing() {
        return normalizing.get();
    }

    public DoubleProperty normalizingProperty() {
        return normalizing;
    }

    public void setNormalizing(double normalizing) {
        this.normalizing.set(normalizing);
    }

    public double getIntegral() {
        return integral.get();
    }

    public DoubleProperty integralProperty() {
        return integral;
    }

    public void setIntegral(double integral) {
        this.integral.set(integral);
    }

    public double getLowLimit() {
        return lowLimit.get();
    }

    public DoubleProperty lowLimitProperty() {
        return lowLimit;
    }

    public void setLowLimit(double lowLimit) {
        this.lowLimit.set(lowLimit);
    }

    public double getHighLimit() {
        return highLimit.get();
    }

    public DoubleProperty highLimitProperty() {
        return highLimit;
    }

    public void setHighLimit(double highLimit) {
        this.highLimit.set(highLimit);
    }

    public void printToConsole() {
        System.out.println("Proportional: " + proportional.get());
        System.out.println("Normalizing: " + normalizing.get());
        System.out.println("Integral: " + integral.get());
        System.out.println("Low Limit: " + lowLimit.get());
        System.out.println("High Limit: " + highLimit.get());
    }
}
