package com.example.biorreactor.Models;

import javafx.beans.property.*;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class DataModel {

    // Atributos

        // Crop name (String)

        // Date (DatePicker)

    // Control mode (ON OR OFF) (True or False)
        private final ArrayList<Boolean> control = new ArrayList<>();

    // Alarms (ABS Low, ABS High, ABS Enable (ON or OFF) (True or False), DEV Low, DEV High, DEV Enable (ON or OFF) (True or False))
        private final ArrayList<ArrayList<DoubleProperty>> alarms = new ArrayList<ArrayList<DoubleProperty>>(); /***/

    // Units
        private final ArrayList<StringProperty> units = new ArrayList<>();

    // LoopName
        private final ArrayList<StringProperty> loopNames = new ArrayList<>();

        // Setpoints and Proccess Values (pH, Temp, O2, Air, Stirrring, pump1, pump2, pump3, pum4)
        private final ArrayList<DoubleProperty> pv = new ArrayList<>();
        private final ArrayList<DoubleProperty> st = new ArrayList<>();

    // Valves
    /***/

    // Constructor
    public DataModel() {

        // LoopNames
        loopNames.add(new SimpleStringProperty("pH"));
        loopNames.add(new SimpleStringProperty("Temperature"));
        loopNames.add(new SimpleStringProperty("Oxygen flow"));
        loopNames.add(new SimpleStringProperty("Air flow"));
        loopNames.add(new SimpleStringProperty("Stirring Rate"));
        loopNames.add(new SimpleStringProperty("Pump 1"));
        loopNames.add(new SimpleStringProperty("Pump 2"));
        loopNames.add(new SimpleStringProperty("Pump 3"));
        loopNames.add(new SimpleStringProperty("Pump 4"));

        // SetPoints and Proccess Values
        for (int i = -1; i < 17; i++) {
            pv.add(new SimpleDoubleProperty(i+2));
            st.add(new SimpleDoubleProperty(i));
        }

        // Alarms
        for (int i = 0; i < 9; i++) {

        }

        // Units
        units.add(new SimpleStringProperty("pH"));
        units.add(new SimpleStringProperty("DegC"));
        units.add(new SimpleStringProperty("SLMP"));
        units.add(new SimpleStringProperty("SLMP"));
        units.add(new SimpleStringProperty("RPM"));
        units.add(new SimpleStringProperty("%"));

        // Control Mode
        for (int i=0; i < 9; i++) {
            control.add(false);
        }

        // Alarms




    }

    // Getters and Setters

    public ObjectProperty<Double> getPvProperty(int i) {
        return pv.get(i).asObject();
    }

    public ObjectProperty<Double> getStProperty(int i) {
        return st.get(i).asObject();
    }

    public ArrayList<StringProperty> getLoopNames() {
        return loopNames;
    }
    public List<DoubleProperty> getSt() {
        return st;
    }

    public List<DoubleProperty> getPv() {
        return pv;
    }
    public ObjectProperty<Boolean> getControlProperty(int index) {
        if (index >= 0 && index < control.size()) {
            SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty(control.get(index));
            return booleanProperty.asObject();
        }
        return new SimpleBooleanProperty(false).asObject();
    }
    public ArrayList<StringProperty> getUnitsProperty() {
        return units;
    }

    public ArrayList<Boolean> getControl() {
        return control;
    }

    public ArrayList<ArrayList<DoubleProperty>> getAlarms() {
        return alarms;
    }

    public ArrayList<StringProperty> getUnits() {
        return units;
    }
}
