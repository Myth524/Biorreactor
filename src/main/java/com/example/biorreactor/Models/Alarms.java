package com.example.biorreactor.Models;

import javafx.beans.property.*;

public class Alarms {

    private final StringProperty loopName;
    private final BooleanProperty absEn;
    private final BooleanProperty devEn;
    private final DoubleProperty absLow;
    private final DoubleProperty absHigh;
    private final DoubleProperty devLow;
    private final DoubleProperty devHigh;

    public Alarms(
            String loopName,
            boolean absEn,
            boolean devEn,
            double absLow,
            double absHigh,
            double devLow,
            double devHigh
    ) {
        this.loopName = new SimpleStringProperty(loopName);
        this.absEn = new SimpleBooleanProperty(absEn);
        this.devEn = new SimpleBooleanProperty(devEn);
        this.absLow = new SimpleDoubleProperty(absLow);
        this.absHigh = new SimpleDoubleProperty(absHigh);
        this.devLow = new SimpleDoubleProperty(devLow);
        this.devHigh = new SimpleDoubleProperty(devHigh);
    }

    public StringProperty loopNameProperty() {
        return loopName;
    }

    public BooleanProperty absEnProperty() {
        return absEn;
    }

    public BooleanProperty devEnProperty() {
        return devEn;
    }

    public DoubleProperty absLowProperty() {
        return absLow;
    }

    public DoubleProperty absHighProperty() {
        return absHigh;
    }

    public DoubleProperty devLowProperty() {
        return devLow;
    }

    public DoubleProperty devHighProperty() {
        return devHigh;
    }
}
