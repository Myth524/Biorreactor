package com.example.biorreactor.Models;

import javafx.beans.property.*;
public class Pump extends Config{

    // Atributos
    private final StringProperty mode; // (Acid, Base, HiFoam, None)
    private IntegerProperty period; // (10s, 15s, 30s)
    /*** Posible variable para calibrar la bomba ***/

    // All args constructor
    public Pump(StringProperty name, DoubleProperty st, DoubleProperty pv, StringProperty mode, StringProperty units) {
        super(name, st, pv, units);
        this.mode = mode;
        this.period = new SimpleIntegerProperty(10);
    }

    // Getters and Setters
    public String getMode() {
        return mode.get();
    }

    public StringProperty modeProperty() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode.set(mode);
    }

    public IntegerProperty periodProperty() {
        return period;
    }

    public void setPeriod(int period) {
        this.period.set(period);
    }

    public IntegerProperty getPeriod() {
        return period;
    }

    public void setPeriod(IntegerProperty period) {
        this.period = period;
    }


    public void printToConsole() {
        System.out.println("Name: " + super.getName());
        System.out.println("Mode: " + mode.get());
        System.out.println("Set Point: " + super.getSt());
        System.out.println("Process Variable: " + super.getPv());
        System.out.println("Control Mode: " + super.isControlMode());
        System.out.println("Period: " + period.get());
    }
}
