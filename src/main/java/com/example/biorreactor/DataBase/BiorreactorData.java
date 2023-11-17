package com.example.biorreactor.DataBase;

public class BiorreactorData {
    private int id;
    private String name;
    private java.sql.Date date;

    public BiorreactorData(int id, String name, java.sql.Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public java.sql.Date getDate() {
        return date;
    }
}
