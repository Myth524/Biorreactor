package com.example.biorreactor.DataBase;

import java.util.List;

public interface IPersistenceHandler {

    public boolean authenticateUser(String username, String password);
    public List<LoopData> getLoopData(int biorreactorId, String loop);
}