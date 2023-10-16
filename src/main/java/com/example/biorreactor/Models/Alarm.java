package com.example.biorreactor.Models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Alarm {

    // Atributos
    private final DoubleProperty absLow;
    private final DoubleProperty absHigh;
    private final BooleanProperty absEn;
    private final DoubleProperty devLow;
    private final DoubleProperty devHigh;
    private final BooleanProperty devEn;

    // No args constructor
    public Alarm() {
        double randomAbsLow = Math.random() * 10; // Change the range as needed
        double randomAbsHigh = Math.random() * 10; // Change the range as needed
        boolean randomAbsEn = Math.random() < 0.5; // 50% chance of being true
        double randomDevLow = Math.random() * 10; // Change the range as needed
        double randomDevHigh = Math.random() * 10; // Change the range as needed
        boolean randomDevEn = Math.random() < 0.5; // 50% chance of being true

        this.absLow = (new SimpleDoubleProperty(randomAbsLow));
        this.absHigh = (new SimpleDoubleProperty(randomAbsHigh));
        this.absEn = (new SimpleBooleanProperty(randomAbsEn));
        this.devLow = (new SimpleDoubleProperty(randomDevLow));
        this.devHigh = (new SimpleDoubleProperty(randomDevHigh));
        this.devEn = (new SimpleBooleanProperty(randomDevEn));
    }

    // Getters and Setters
    public double getAbsLow() {
        return absLow.get();
    }

    public DoubleProperty absLowProperty() {
        return absLow;
    }

    public void setAbsLow(double absLow) {
        this.absLow.set(absLow);
    }

    public double getAbsHigh() {
        return absHigh.get();
    }

    public DoubleProperty absHighProperty() {
        return absHigh;
    }

    public void setAbsHigh(double absHigh) {
        this.absHigh.set(absHigh);
    }

    public boolean isAbsEn() {
        return absEn.get();
    }

    public BooleanProperty absEnProperty() {
        return absEn;
    }

    public void setAbsEn(boolean absEn) {
        this.absEn.set(absEn);
    }

    public double getDevLow() {
        return devLow.get();
    }

    public DoubleProperty devLowProperty() {
        return devLow;
    }

    public void setDevLow(double devLow) {
        this.devLow.set(devLow);
    }

    public double getDevHigh() {
        return devHigh.get();
    }

    public DoubleProperty devHighProperty() {
        return devHigh;
    }

    public void setDevHigh(double devHigh) {
        this.devHigh.set(devHigh);
    }

    public boolean isDevEn() {
        return devEn.get();
    }

    public BooleanProperty devEnProperty() {
        return devEn;
    }

    public void setDevEn(boolean devEn) {
        this.devEn.set(devEn);
    }

    public void printToConsole() {
        System.out.println("Absolute Low: " + absLow.get());
        System.out.println("Absolute High: " + absHigh.get());
        System.out.println("Absolute Enable: " + absEn.get());
        System.out.println("Deviation Low: " + devLow.get());
        System.out.println("Deviation High: " + devHigh.get());
        System.out.println("Deviation Enable: " + devEn.get());
    }
}
