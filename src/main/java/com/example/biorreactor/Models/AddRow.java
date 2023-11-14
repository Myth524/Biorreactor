package com.example.biorreactor.Models;

import javafx.beans.property.*;

import java.util.ArrayList;

public class AddRow {

    private final StringProperty loopName;
    private final DoubleProperty st;
    private final DoubleProperty pv;
    private final BooleanProperty controlMode;
    private ArrayList<Alarm> alarms;
    private final StringProperty units;
    private final DoubleProperty absLow;
    private final DoubleProperty absHigh;
    private final BooleanProperty absEn;
    private final DoubleProperty devLow;
    private final DoubleProperty devHigh;
    private final BooleanProperty devEn;

    public AddRow() {
        this.loopName = new SimpleStringProperty();
        this.pv = new SimpleDoubleProperty();
        this.st = new SimpleDoubleProperty();
        this.controlMode = new SimpleBooleanProperty();
        this.units = new SimpleStringProperty();
        this.absLow = (new SimpleDoubleProperty());
        this.absHigh = (new SimpleDoubleProperty());
        this.absEn = (new SimpleBooleanProperty());
        this.devLow = (new SimpleDoubleProperty());
        this.devHigh = (new SimpleDoubleProperty());
        this.devEn = (new SimpleBooleanProperty());
        this.alarms = null;
    }

    public String getLoopName() {
        return loopName.get();
    }

    public StringProperty loopNameProperty() {
        return loopName;
    }

    public void setLoopName(String loopName) {
        this.loopName.set(loopName);
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

    public ArrayList<Alarm> getAlarms() {
        return alarms;
    }

    public void setAlarms(ArrayList<Alarm> alarms) {
        this.alarms = alarms;
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

    public Alarm getAlarm() {
        if (alarms != null && !alarms.isEmpty()) {
            return alarms.get(alarms.size() - 1);
        }
        return null;
    }
}
