package com.example.biorreactor.Models;

public class SummaryDataRow {
    private String loopName;
    private double pv;
    private double st;
    private boolean control;
    private String units;

    // Getters and setters
    public String getLoopName() {
        return loopName;
    }

    public void setLoopName(String loopName) {
        this.loopName = loopName;
    }

    public double getPv() {
        return pv;
    }

    public void setPv(double pv) {
        this.pv = pv;
    }

    public double getSt() {
        return st;
    }

    public void setSt(double st) {
        this.st = st;
    }

    public boolean isControl() {
        return control;
    }

    public void setControl(boolean control) {
        this.control = control;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
