package com.example.biorreactor.Models;

import javafx.beans.property.*;

import java.util.ArrayList;

public class Loop extends Config{

    // Atributos
    private ArrayList<Alarm> alarms; // (ABS low, ABS high, ABS Enable, DEV low, DEV high, DEV Enable)
    private PID pid;

    // No args constructor
    public Loop(StringProperty loopName, DoubleProperty st, DoubleProperty pv, StringProperty units) {
        super(loopName, st, pv, units);
        this.pid = new PID();
        this.alarms = new ArrayList<>();
        this.alarms.add(new Alarm());
    }

    // Getters and Setters
    public ArrayList<Alarm> getAlarms() {
        return alarms;
    }

    public void setAlarms(ArrayList<Alarm> alarms) {
        this.alarms = alarms;
    }

    public PID getPid() {
        return pid;
    }

    public void setPid(PID pid) {
        this.pid = pid;
    }

    public void printToConsole() {
        System.out.println("Loop Name: " + super.getName());
        System.out.println("Set Point: " + super.getSt());
        System.out.println("Process Variable: " + super.getPv());
        System.out.println("Control Mode: " + super.isControlMode());
        System.out.println("Units: " + super.getUnits());
        System.out.println("Alarms:");
        for (Alarm alarm : alarms) {
            alarm.printToConsole();
        }
        System.out.println("PID Parameters:");
        pid.printToConsole();
    }
}
