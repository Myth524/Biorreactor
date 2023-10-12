package com.example.biorreactor.Models;

import javafx.beans.property.*;

public class AlarmsDataRow {
    private final StringProperty loopName;
    private final DoubleProperty absLow;
    private final DoubleProperty absHigh;
    private final BooleanProperty absEn;
    private final DoubleProperty devLow;
    private final DoubleProperty devHigh;
    private final BooleanProperty devEn;

    public AlarmsDataRow(String loopName, double absLow, double absHigh, double absEn, double devLow, double devHigh, double devEn) {
        this.loopName = new SimpleStringProperty(loopName);
        this.absLow = new SimpleDoubleProperty(absLow);
        this.absHigh = new SimpleDoubleProperty(absHigh);
        this.absEn = new SimpleBooleanProperty(absEn != 0);
        this.devLow = new SimpleDoubleProperty(devLow);
        this.devHigh = new SimpleDoubleProperty(devHigh);
        this.devEn = new SimpleBooleanProperty(devEn != 0);
    }

    public StringProperty loopNameProperty() {
        return loopName;
    }

    public DoubleProperty absLowProperty() {
        return absLow;
    }

    public DoubleProperty absHighProperty() {
        return absHigh;
    }

    public BooleanProperty absEnProperty() {
        return absEn;
    }

    public DoubleProperty devLowProperty() {
        return devLow;
    }

    public DoubleProperty devHighProperty() {
        return devHigh;
    }

    public BooleanProperty devEnProperty() {
        return devEn;
    }
}
