package com.example.biorreactor.Models;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
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
    public Loop getLoopByName(String name) {
        int i=0;
        while (i < loops.size() && !loops.get(i).getName().equals(name)){
            i++;
        }
        if(i<loops.size()){
            return loops.get(i);
        }else {
            return null;
        }
    }

    public int getLoopPos(String name){
        int i=0;
        while (i < loops.size() && !loops.get(i).getName().equals(name)){
            i++;
        }
        return i;
    }
    public Pump getPumpsByName(String name) {
        int i=0;
        while (i < pumps.size() && !pumps.get(i).getName().equals(name)){
            i++;
        }
        if(i<pumps.size()){
            return pumps.get(i);
        }else {
            return null;
        }
    }

    public void shutDownLoop (ArrayList<Loop> loops) {
        for (Loop loop : loops) {
            getLoopByName(loop.getName()).setSt(0);
        }
    }

    public void shutDownPumps (ArrayList<Pump> pumps) {
        for (Pump pump : pumps) {
            getPumpsByName(pump.getName()).setSt(0);
        }
    }

    public boolean checkAlarmsCondition(Alarm alarms, Loop loop) {
        double loopValue = loop.pvProperty().get();
        return Double.compare(alarms.getAbsHigh(), loopValue) == 0 ||
                Double.compare(alarms.getAbsLow(), loopValue) == 0 ||
                Double.compare(alarms.getDevLow(), loopValue) == 0 ||
                Double.compare(alarms.getDevHigh(), loopValue) == 0;

    }

    public void checkAlarms() {
        for (Loop loop : loops) {
            Alarm alarms = loop.getAlarms().get(loop.getAlarms().size()-1);
                if (alarms.isActive() && alarms.isOn() && checkAlarmsCondition(alarms, loop)) {
                    if (!alarms.getLoopsSD().isEmpty()) {
                        shutDownLoop(alarms.getLoopsSD());
                    }
                    if (!alarms.getPumpsSD().isEmpty()) {
                        shutDownPumps(alarms.getPumpsSD());
                    }
                    if (alarms.isAbsEn() || alarms.isDevEn()) {
                        showErrorAlert(loop);
                    }
                    alarms.setActive(false);
                    alarms.setTriggered(true);
                }
        }
    }

    public void showErrorAlert(Loop loop) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Se disparó la alarma de: " + loop.getName());
            alert.showAndWait();
        });
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
