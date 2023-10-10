package com.example.biorreactor.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class DataModel {

    // Atributos
    private final DoubleProperty stPh;
    private final DoubleProperty pvPh;
    private final DoubleProperty stTemp;
    private final DoubleProperty pvTemp;

    private final DoubleProperty stO2;
    private final DoubleProperty pvO2;

    private final DoubleProperty stStirring;
    private final DoubleProperty pvStirring;

    private final DoubleProperty stPump1;
    private final DoubleProperty pvPump1;

    private final DoubleProperty stPump2;
    private final DoubleProperty pvPump2;

    private final DoubleProperty stPump3;
    private final DoubleProperty pvPump3;

    private final DoubleProperty stPump4;
    private final DoubleProperty pvPump4;

    private final DoubleProperty stAir;
    private final DoubleProperty pvAir;

    // Constructor
    public DataModel() {
        this.stPh = new SimpleDoubleProperty(0);
        this.pvPh = new SimpleDoubleProperty(0);

        this.stTemp = new SimpleDoubleProperty(1);
        this.pvTemp = new SimpleDoubleProperty(1);

        this.stO2 = new SimpleDoubleProperty(2);
        this.pvO2 = new SimpleDoubleProperty(2);

        this.stStirring = new SimpleDoubleProperty(3);
        this.pvStirring = new SimpleDoubleProperty(3);

        this.stPump1 = new SimpleDoubleProperty(4);
        this.pvPump1 = new SimpleDoubleProperty(4);

        this.stPump2 = new SimpleDoubleProperty(5);
        this.pvPump2 = new SimpleDoubleProperty(5);

        this.stPump3 = new SimpleDoubleProperty(6);
        this.pvPump3 = new SimpleDoubleProperty(6);

        this.stPump4 = new SimpleDoubleProperty(7);
        this.pvPump4 = new SimpleDoubleProperty(7);

        this.stAir = new SimpleDoubleProperty(8);
        this.pvAir = new SimpleDoubleProperty(8);
    }

    // Getters and Setters
    public double getStPh() {
        return stPh.get();
    }

    public void setStPh(double stPh) {
        this.stPh.set(stPh);
    }

    public DoubleProperty stPropertyPh() {
        return stPh;
    }

    public double getPvPh() {
        return pvPh.get();
    }

    public void setPvPh(double pvPh) {
        this.pvPh.set(pvPh);
    }

    public DoubleProperty pvPropertyPh() {
        return pvPh;
    }
    public double getStTemp() {
        return stTemp.get();
    }

    public void setStTemp(double stTemp) {
        this.stTemp.set(stTemp);
    }

    public DoubleProperty stPropertyTemp() {
        return stTemp;
    }

    public double getPvTemp() {
        return pvTemp.get();
    }

    public void setPvTemp(double pvTemp) {
        this.pvTemp.set(pvTemp);
    }

    public DoubleProperty pvPropertyTemp() {
        return pvTemp;
    }

    public double getStO2() {
        return stO2.get();
    }

    public void setStO2(double stO2) {
        this.stO2.set(stO2);
    }

    public DoubleProperty stPropertyO2() {
        return stO2;
    }

    public double getPvO2() {
        return pvO2.get();
    }

    public void setPvO2(double pvO2) {
        this.pvO2.set(pvO2);
    }

    public DoubleProperty pvPropertyO2() {
        return pvO2;
    }

    public double getStStirring() {
        return stStirring.get();
    }

    public void setStStirring(double stStirring) {
        this.stStirring.set(stStirring);
    }

    public DoubleProperty stPropertyStirring() {
        return stStirring;
    }

    public double getPvStirring() {
        return pvStirring.get();
    }

    public void setPvStirring(double pvStirring) {
        this.pvStirring.set(pvStirring);
    }

    public DoubleProperty pvPropertyStirring() {
        return pvStirring;
    }

    public double getStPump1() {
        return stPump1.get();
    }

    public void setStPump1(double stPump1) {
        this.stPump1.set(stPump1);
    }

    public DoubleProperty stPropertyPump1() {
        return stPump1;
    }

    public double getPvPump1() {
        return pvPump1.get();
    }

    public void setPvPump1(double pvPump1) {
        this.pvPump1.set(pvPump1);
    }

    public DoubleProperty pvPropertyPump1() {
        return pvPump1;
    }

    public double getStPump2() {
        return stPump2.get();
    }

    public void setStPump2(double stPump2) {
        this.stPump2.set(stPump2);
    }

    public DoubleProperty stPropertyPump2() {
        return stPump2;
    }

    public double getPvPump2() {
        return pvPump2.get();
    }

    public void setPvPump2(double pvPump2) {
        this.pvPump2.set(pvPump2);
    }

    public DoubleProperty pvPropertyPump2() {
        return pvPump2;
    }

    public double getStPump3() {
        return stPump3.get();
    }

    public void setStPump3(double stPump3) {
        this.stPump3.set(stPump3);
    }

    public DoubleProperty stPropertyPump3() {
        return stPump3;
    }

    public double getPvPump3() {
        return pvPump3.get();
    }

    public void setPvPump3(double pvPump3) {
        this.pvPump3.set(pvPump3);
    }

    public DoubleProperty pvPropertyPump3() {
        return pvPump3;
    }

    public double getStPump4() {
        return stPump4.get();
    }

    public void setStPump4(double stPump4) {
        this.stPump4.set(stPump4);
    }

    public DoubleProperty stPropertyPump4() {
        return stPump4;
    }

    public double getPvPump4() {
        return pvPump4.get();
    }

    public void setPvPump4(double pvPump4) {
        this.pvPump4.set(pvPump4);
    }

    public DoubleProperty pvPropertyPump4() {
        return pvPump4;
    }

    public double getStAir() {
        return stAir.get();
    }

    public void setStAir(double stAir) {
        this.stAir.set(stAir);
    }

    public DoubleProperty stPropertyAir() {
        return stAir;
    }

    public double getPvAir() {
        return pvAir.get();
    }

    public void setPvAir(double pvAir) {
        this.pvAir.set(pvAir);
    }

    public DoubleProperty pvPropertyAir() {
        return pvAir;
    }

}
