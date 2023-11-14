package com.example.biorreactor.Models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;

public class Alarm {

    // Atributos
    private final DoubleProperty absLow;
    private final DoubleProperty absHigh;
    private final BooleanProperty absEn;
    private final BooleanProperty absAu;
    private final DoubleProperty devLow;
    private final DoubleProperty devHigh;
    private final BooleanProperty devEn;
    private final BooleanProperty devAu;
    private final ArrayList<Loop> loopsSD;
    private final ArrayList<Pump> pumpsSD;
    private final BooleanProperty active;
    private final BooleanProperty on;
    private final BooleanProperty triggered;

    // No args constructor
    public Alarm() {
/*
        double randomAbsLow = Math.random() * 10; // Change the range as needed
        double randomAbsHigh = Math.random() * 10; // Change the range as needed
        boolean randomAbsEn = Math.random() < 0.5; // 50% chance of being true
        boolean randomAbsAu = Math.random() < 0.5; // 50% chance of being true
        double randomDevLow = Math.random() * 10; // Change the range as needed
        double randomDevHigh = Math.random() * 10; // Change the range as needed
        boolean randomDevEn = Math.random() < 0.5; // 50% chance of being true
        boolean randomDevAu = Math.random() < 0.5; // 50% chance of being true*/

        this.absLow = new SimpleDoubleProperty(0);
        this.absHigh = new SimpleDoubleProperty(0);
        this.absEn = new SimpleBooleanProperty(false);
        this.absAu = new SimpleBooleanProperty(false);
        this.devLow = new SimpleDoubleProperty(0);
        this.devHigh = new SimpleDoubleProperty(0);
        this.devEn = new SimpleBooleanProperty(false);
        this.devAu = new SimpleBooleanProperty(false);

        this.loopsSD = new ArrayList<>();
        this.pumpsSD = new ArrayList<>();

        this.active = new SimpleBooleanProperty(false);
        this.on = new SimpleBooleanProperty(false);
        this.triggered = new SimpleBooleanProperty(false);

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

    public boolean isAbsAu() {
        return absAu.get();
    }

    public BooleanProperty absAuProperty() {
        return absAu;
    }

    public void setAbsAu(boolean absAu) {
        this.absAu.set(absAu);
    }

    public boolean isDevAu() {
        return devAu.get();
    }

    public BooleanProperty devAuProperty() {
        return devAu;
    }

    public void setDevAu(boolean devAu) {
        this.devAu.set(devAu);
    }

    public boolean isActive() {
        return active.get();
    }

    public BooleanProperty activeProperty() {
        return active;
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }

    public ArrayList<Loop> getLoopsSD() {
        return loopsSD;
    }

    public ArrayList<Pump> getPumpsSD() {
        return pumpsSD;
    }
    public void addLoopSD(Loop loop) {
        loopsSD.add(loop);
    }

    public void removeLoopSD(Loop loop) {
        loopsSD.remove(loop);
    }

    public void addPumpSD(Pump pump) {
        pumpsSD.add(pump);
    }

    public void removePumpSD(Pump pump) {
        pumpsSD.remove(pump);
    }

    public boolean isOn() {
        return on.get();
    }

    public BooleanProperty onProperty() {
        return on;
    }

    public void setOn(boolean on) {
        this.on.set(on);
    }

    public boolean isTriggered() {

        return triggered.get();
    }

    public BooleanProperty triggeredProperty() {
        return triggered;
    }

    public void setTriggered(boolean triggered) {
        this.triggered.set(triggered);
    }

    public void printToConsole() {
        System.out.println("Absolute Low: " + absLow.get());
        System.out.println("Absolute High: " + absHigh.get());
        System.out.println("Absolute Enable: " + absEn.get());
        System.out.println("Absolute Audible: " + absAu.get());
        System.out.println("Deviation Low: " + devLow.get());
        System.out.println("Deviation High: " + devHigh.get());
        System.out.println("Deviation Enable: " + devEn.get());
        System.out.println("Deviation Audible: " + devAu.get());
    }
}
