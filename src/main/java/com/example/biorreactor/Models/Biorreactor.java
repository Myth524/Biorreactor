package com.example.biorreactor.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;

import java.util.ArrayList;

public class Biorreactor {

    // Instancia
    private static Biorreactor biorreactor;

    public static synchronized Biorreactor getInstance() {
        if (biorreactor == null) {
            biorreactor = new Biorreactor();
        }
        return biorreactor;
    }

    // Atributos
    private StringProperty cropName;
    private DatePicker date;
    private ObservableList<Loop> loops = FXCollections.observableArrayList();
    private ObservableList<Pump> pumps = FXCollections.observableArrayList();
    private ArrayList<Calibration> calibrations;


    // No arg constructor
    private Biorreactor() {
        initialize();
        printToConsole();
    }

    public void initialize() {
        this.cropName = new SimpleStringProperty("");
        this.date = new DatePicker();
        this.calibrations = new ArrayList<>();

        StringProperty loopName = new SimpleStringProperty("pH");
        StringProperty unit = new SimpleStringProperty("pH");
        DoubleProperty st = new SimpleDoubleProperty(1);
        DoubleProperty pv = new SimpleDoubleProperty(2);
        this.loops.add(new Loop(loopName, st,pv,unit));

        st = new SimpleDoubleProperty(3);
        pv = new SimpleDoubleProperty(4);
        loopName = new SimpleStringProperty("Temperature");
        unit = new SimpleStringProperty("DegC");
        this.loops.add(new Loop(loopName, st,pv,unit));

        st = new SimpleDoubleProperty(5);
        pv = new SimpleDoubleProperty(6);
        loopName = new SimpleStringProperty("DO");
        unit = new SimpleStringProperty("%DO");
        this.loops.add(new Loop(loopName, st,pv,unit));

        st = new SimpleDoubleProperty(7);
        pv = new SimpleDoubleProperty(8);
        loopName = new SimpleStringProperty("Stirring Rate");
        unit = new SimpleStringProperty("RPM");
        this.loops.add(new Loop(loopName, st,pv,unit));

        // Pumps
        unit = new SimpleStringProperty("%");

        st = new SimpleDoubleProperty(9);
        pv = new SimpleDoubleProperty(10);
        StringProperty name = new SimpleStringProperty("Pump 1");
        StringProperty mode = new SimpleStringProperty("Acid");
        this.pumps.add(new Pump(name, st, pv, mode, unit));

        st = new SimpleDoubleProperty(11);
        pv = new SimpleDoubleProperty(12);
        name = new SimpleStringProperty("Pump 2");
        mode = new SimpleStringProperty("Base");
        this.pumps.add(new Pump(name, st, pv, mode, unit));

        st = new SimpleDoubleProperty(14);
        pv = new SimpleDoubleProperty(13);
        name = new SimpleStringProperty("Pump 3");
        mode = new SimpleStringProperty("HiFoam");
        this.pumps.add(new Pump(name, st, pv, mode, unit));

        // Calibration
        loopName = new SimpleStringProperty("pH");
        this.calibrations.add(new Calibration(loopName));

        loopName = new SimpleStringProperty("DO");
        this.calibrations.add(new Calibration(loopName));
    }

    // Getters and Setters
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

    public void setDate(DatePicker date) {
        this.date = date;
    }

    public ObservableList<Loop> getLoops() {
        return loops;
    }

    public void setLoops(ObservableList<Loop> loops) {
        this.loops = loops;
    }

    public ObservableList<Pump> getPumps() {
        return pumps;
    }

    public void setPumps(ObservableList<Pump> pumps) {
        this.pumps = pumps;
    }

    public ArrayList<Calibration> getCalibrations() {
        return calibrations;
    }

    public void setCalibrations(ArrayList<Calibration> calibrations) {
        this.calibrations = calibrations;
    }
    public void printToConsole() {
        System.out.println("Crop Name: " + cropName.get());
        System.out.println("Date: " + date.getValue());
        System.out.println("Loops:");
        for (Loop loop : loops) {
            loop.printToConsole();
        }
        System.out.println("Pumps:");
        for (Pump pump : pumps) {
            pump.printToConsole();
        }
        System.out.println("Calibrations:");
        for (Calibration calibration : calibrations) {
            calibration.printToConsole();
        }
    }
}
