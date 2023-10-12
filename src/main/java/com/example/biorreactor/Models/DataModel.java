package com.example.biorreactor.Models;

import javafx.beans.property.*;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataModel {

    private final List<SummaryDataRow> Srows = new ArrayList<>();
    private final List<AlarmsDataRow> Arows = new ArrayList<>();

    private static DataModel dataModel;

    public static synchronized DataModel getInstance() {
        if (dataModel == null) {
            dataModel = new DataModel();
        }
        return dataModel;
    }

    // Atributos

        // Crop name (String)
        private final StringProperty cropName;

        // Date (DatePicker)
        private final DatePicker date;

    // Control mode (ON OR OFF) (True or False)
        private final ArrayList<Boolean> control = new ArrayList<>();

    // Alarms (ABS Low, ABS High, ABS Enable (ON or OFF) (True or False), DEV Low, DEV High, DEV Enable (ON or OFF) (True or False))
        private final ArrayList<ArrayList<DoubleProperty>> alarms = new ArrayList<>(); /***/

    // Units
        private final ArrayList<StringProperty> units = new ArrayList<>();

    // LoopName
        private final ArrayList<StringProperty> loopNames = new ArrayList<>();

        // Setpoints and Proccess Values (pH, Temp, DO, Stirrring, pump1, pump2, pump3)
        private final ArrayList<DoubleProperty> pv = new ArrayList<>();
        private final ArrayList<DoubleProperty> st = new ArrayList<>();

    // Constructor
    public DataModel() {

        // Cropname
        this.cropName = (new SimpleStringProperty(""));

        // Datepicker
        this.date = (new DatePicker());

        System.out.println("Valor variables inicializadas: " + "\n");

        // LoopNames
        loopNames.add(new SimpleStringProperty("pH"));
        loopNames.add(new SimpleStringProperty("Temperature"));
        loopNames.add(new SimpleStringProperty("DO"));
        loopNames.add(new SimpleStringProperty("Stirring Rate"));
        loopNames.add(new SimpleStringProperty("Pump 1 (Acid)"));
        loopNames.add(new SimpleStringProperty("Pump 2 (Base)"));
        loopNames.add(new SimpleStringProperty("Pump 3 (HiFoam)"));

        System.out.println("Loop names:");
        for (StringProperty stringProperty : loopNames) {
            System.out.println(stringProperty.get());
        }

        // SetPoints and Proccess Values
         /*** se deben inicializan en 0 ***/

        for (int i = 0; i < loopNames.size(); i++) {
            pv.add(new SimpleDoubleProperty(i+1));
            st.add(new SimpleDoubleProperty(i+1));
        }
        /*** Esto no va ***/
        System.out.println("\nSetPoints and PV: ");
        for(int i=0; i<loopNames.size();i++){
            System.out.println(loopNames.get(i).get() + ": " + "PV: " + pv.get(i).get() + "\tST: " + st.get(i).get() + "\n");
        }

        // Units
        units.add(new SimpleStringProperty("pH"));
        units.add(new SimpleStringProperty("DegC"));
        units.add(new SimpleStringProperty("%DO"));
        units.add(new SimpleStringProperty("RPM"));
        units.add(new SimpleStringProperty("%"));
        units.add(new SimpleStringProperty("%"));
        units.add(new SimpleStringProperty("%"));

        // Control Mode
        /*** Se debe inicializar en false ***/
        for (int i=0; i < loopNames.size(); i++) {
            control.add(false);
        }

        // Alarms
        /*** Se inicializa un arraylist vacio ***/

        System.out.println("\nValores Alarmas: ");

        for (int i = 0; i < loopNames.size(); i++) {
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
            for (DoubleProperty doubleProperty : alarmList) {
                System.out.print(doubleProperty.get() + " ");
            }
            System.out.println();
        }

    }

    // Getters and Setters

    public ObjectProperty<Double> getPvProperty(int i) {
        if (i >= 0 && i < pv.size()) {
            return pv.get(i).asObject();
        }
        return new SimpleDoubleProperty(0).asObject();
    }

    public ObjectProperty<Double> getStProperty(int i) {
        if (i >= 0 && i < st.size()) {
            return st.get(i).asObject();
        }
        return new SimpleDoubleProperty(0).asObject();
    }

    public ArrayList<StringProperty> getLoopNames() {
        return loopNames;
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

    public String getCropName() {
        return cropName.get();
    }

    public StringProperty cropNameProperty() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName.set(cropName);
    }

    public DatePicker getDate() {
        return date;
    }

}
