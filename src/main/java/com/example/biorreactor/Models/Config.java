package com.example.biorreactor.Models;

import javafx.beans.property.*;

public class Config {

    // Atributos
    private final StringProperty name;
    private final DoubleProperty st;
    private final DoubleProperty pv;
    private final BooleanProperty controlMode;
    private final StringProperty units;

    // All args constructor
    public Config (StringProperty name, DoubleProperty st, DoubleProperty pv, StringProperty units) {
        this.name = name;
        this.st = st;
        this.pv = pv;
        this.controlMode = new SimpleBooleanProperty(false);
        this.units = units;
    }

    // Getters and Setters
    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getSt() {
        return st.get();
    }

    public DoubleProperty stProperty() {
        return st;
    }

    public void setSt(double st) {
        this.st.set(st);
    }

    public double getPv() {
        return pv.get();
    }

    public DoubleProperty pvProperty() {
        return pv;
    }

    public void setPv(double pv) {
        this.pv.set(pv);
    }

    public boolean isControlMode() {
        return controlMode.get();
    }

    public BooleanProperty controlModeProperty() {
        return controlMode;
    }

    public void setControlMode(boolean controlMode) {
        this.controlMode.set(controlMode);
    }

    public String getUnits() {
        return units.get();
    }

    public StringProperty unitsProperty() {
        return units;
    }

    public void setUnits(String units) {
        this.units.set(units);
    }
}
