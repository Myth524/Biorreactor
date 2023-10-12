package com.example.biorreactor.Models;

import javafx.beans.property.*;
import javafx.scene.control.DatePicker;

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
        private final ArrayList<Alarms> alarms = new ArrayList<>(); /***/

    // Units
        private final ArrayList<StringProperty> units = new ArrayList<>();

    // LoopName
        private final ArrayList<StringProperty> loopNames = new ArrayList<>();

        // Setpoints and Proccess Values (pH, Temp, DO, Stirrring, pump1, pump2, pump3)
        private final ArrayList<DoubleProperty> pv = new ArrayList<>();
        private final ArrayList<DoubleProperty> st = new ArrayList<>();

    // Constructor
    public DataModel() {

        System.out.println("Valor variables inicializadas: " + "\n");

        // Cropname
        this.cropName = (new SimpleStringProperty(""));
        System.out.println("Crop name: " + cropName.get() + "\n");

        // Datepicker
        this.date = (new DatePicker());
        System.out.println("Date: " + date.getValue() + "\n");

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
            alarms.add(new Alarms(loopNames.get(i).get(), false, false, 0, 0, 0, 0));
        }

        /*** Esto no va ***/
        for (int i = 0; i < loopNames.size(); i++) {
            System.out.println("Fila " + i + ": ");
            Alarms alarm = alarms.get(i);
            System.out.println("Loop Name: " + alarm.loopNameProperty().get());
            System.out.println("ABS Low: " + alarm.absLowProperty().get());
            System.out.println("ABS High: " + alarm.absHighProperty().get());
            System.out.println("ABS Enable: " + alarm.absEnProperty().get());
            System.out.println("DEV Low: " + alarm.devLowProperty().get());
            System.out.println("DEV High: " + alarm.devHighProperty().get());
            System.out.println("DEV Enable: " + alarm.devEnProperty().get());
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

    public ArrayList<Alarms> getAlarms() {
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

    public ArrayList<DoubleProperty> getPv() {
        return pv;
    }

    public ArrayList<DoubleProperty> getSt() {
        return st;
    }
}
