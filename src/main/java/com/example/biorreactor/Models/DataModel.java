package com.example.biorreactor.Models;

import com.example.biorreactor.Views.ViewFactory;
import javafx.beans.property.*;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class DataModel {

    private List<SummaryDataRow> rows = new ArrayList<>();
    private static DataModel dataModel;

    public static synchronized DataModel getInstance() {
        if (dataModel == null) {
            dataModel = new DataModel();
        }
        return dataModel;
    }

    // Atributos

        // Crop name (String)

        // Date (DatePicker)

    // Control mode (ON OR OFF) (True or False)
        private final ArrayList<Boolean> control = new ArrayList<>();

    // Alarms (ABS Low, ABS High, ABS Enable (ON or OFF) (True or False), DEV Low, DEV High, DEV Enable (ON or OFF) (True or False))
        private final ArrayList<ArrayList<DoubleProperty>> alarms = new ArrayList<>(); /***/

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

        System.out.println("Valor variables inicializadas: ");

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

        System.out.println("Loop names:");
        for (StringProperty stringProperty : loopNames) {
            System.out.println(stringProperty);
        }

        // SetPoints and Proccess Values
        for (int i = -1; i < 17; i++) {
            pv.add(new SimpleDoubleProperty(i+2));
            st.add(new SimpleDoubleProperty(i));
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

        System.out.println("\nValores Alarmas: ");

        for (int i = 0; i < 9; i++) {
            ArrayList<DoubleProperty> alarmList = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                DoubleProperty doubleProperty = new SimpleDoubleProperty(0);
                alarmList.add(doubleProperty);
            }
            alarms.add(alarmList);
        }

        /*** Esto no va ***/
        for (int i = 0; i < alarms.size(); i++) {
            ArrayList<DoubleProperty> alarmList = alarms.get(i);
            System.out.print("Fila " + i + ": ");
            for (int j = 0; j < alarmList.size(); j++) {
                DoubleProperty doubleProperty = alarmList.get(j);
                System.out.print(doubleProperty.get() + " ");
            }
            System.out.println();
        }

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

    public void addDataRow(SummaryDataRow row) {
        rows.add(row);
    }

    public SummaryDataRow getDataRow(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < rows.size()) {
            return rows.get(rowIndex);
        }
        return null;
    }

}
