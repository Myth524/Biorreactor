package com.example.biorreactor.DataBase;

public interface IPersistenceHandler {

    public boolean authenticateUser(String username, String password);
}