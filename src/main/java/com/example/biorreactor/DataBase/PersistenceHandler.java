package com.example.biorreactor.DataBase;


import com.example.biorreactor.Models.*;

import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenceHandler implements IPersistenceHandler {
    private static PersistenceHandler instance;
    private String url = "localhost";
    private int port = 5432;
    private String databaseName = "Biorreactores";
    private String username = "postgres";
    private String password = "1234";
    private Connection connection = null;

    private PersistenceHandler(){
        initializePostgresqlDatabase();
    }

    public static PersistenceHandler getInstance(){
        if (instance == null) {
            instance = new PersistenceHandler();
        }
        return instance;
    }

    private void initializePostgresqlDatabase() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection("jdbc:postgresql://" + url + ":" + port + "/" + databaseName, username, password);
            System.out.println("DB connected");
        } catch (SQLException | IllegalArgumentException ex) {
            ex.printStackTrace(System.err);
        } finally {
            if (connection == null) {
                System.exit(-1);
            }
        }
    }


    @Override
    public boolean authenticateUser(String username, String password) {
        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            return false;
        }
    }

    @Override
    public List<LoopData> getLoopData(int biorreactorId, String loop) {
        List<LoopData> data = new ArrayList<>();
        try {
            String query = "SELECT timestamp, processValue FROM Loops WHERE loopName = ? AND FK_biorreactor_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, loop);
                preparedStatement.setInt(2, biorreactorId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Timestamp timestamp = resultSet.getTimestamp("timestamp");
                        double processValue = resultSet.getDouble("processValue");
                        data.add(new LoopData(timestamp.toLocalDateTime(), processValue));
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return data;
    }

    public void insertLoop(int biorreactorId, String loopName, double setpoint, double processValue) {
        String insertLoopQuery = "INSERT INTO Loops (loopName, setpoint, processValue, FK_biorreactor_id, timestamp) VALUES (?, ?, ?, ?, NOW())";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertLoopQuery);
            preparedStatement.setString(1, loopName);
            preparedStatement.setDouble(2, setpoint);
            preparedStatement.setDouble(3, processValue);
            preparedStatement.setInt(4, biorreactorId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /***

     -- Crear la tabla 'Users'
     CREATE TABLE Users (
     user_id SERIAL PRIMARY KEY,
     username VARCHAR(255),
     password VARCHAR(255)
     );

     -- Crear la tabla 'Biorreactor'
     CREATE TABLE Biorreactor (
     biorreactor_id SERIAL PRIMARY KEY,
     nombre VARCHAR(255),
     fecha_creacion DATE,
     FK_user_id INT,
     FOREIGN KEY (FK_user_id) REFERENCES Users(user_id)
     );

     -- Crear la tabla 'Loops' con el campo 'timestamp'
     CREATE TABLE Loops (
     loop_id SERIAL PRIMARY KEY,
     loopName VARCHAR(255),
     setpoint DECIMAL,
     processValue DECIMAL,
     FK_biorreactor_id INT,
     timestamp TIMESTAMP,
     FOREIGN KEY (FK_biorreactor_id) REFERENCES Biorreactor(biorreactor_id)
     );

     -- Crear la tabla 'Alarms'
     CREATE TABLE Alarms (
     alarm_id SERIAL PRIMARY KEY,
     absHigh DECIMAL,
     absLow DECIMAL,
     absEn BOOLEAN,
     devLow DECIMAL,
     devHigh DECIMAL,
     devEn BOOLEAN,
     isActive BOOLEAN,
     FK_loop_id INT,
     FOREIGN KEY (FK_loop_id) REFERENCES Loops(loop_id)
     );

     -- Crear la tabla 'PIDs'
     CREATE TABLE PIDs (
     pid_id SERIAL PRIMARY KEY,
     normalizing DECIMAL,
     proportional DECIMAL,
     integral DECIMAL,
     lowLimit DECIMAL,
     highLimit DECIMAL,
     FK_loop_id INT,
     FOREIGN KEY (FK_loop_id) REFERENCES Loops(loop_id)
     );

     -- Crear la tabla 'Calibrations'
     CREATE TABLE Calibrations (
     calibration_id SERIAL PRIMARY KEY,
     probeName VARCHAR(255),
     span DECIMAL,
     zero DECIMAL,
     FK_biorreactor_id INT,
     FOREIGN KEY (FK_biorreactor_id) REFERENCES Biorreactor(biorreactor_id)
     );

     -- Crear la tabla 'Pumps'
     CREATE TABLE Pumps (
     pump_id SERIAL PRIMARY KEY,
     pumpName VARCHAR(255),
     pumpMode VARCHAR(50),
     setpoint DECIMAL,
     processValue DECIMAL,
     pumpPeriod INT,
     controlMode BOOLEAN,
     timestamp TIMESTAMP,
     FK_biorreactor_id INT,
     FOREIGN KEY (FK_biorreactor_id) REFERENCES Biorreactor(biorreactor_id)
     );

     -- Insertar un dato en la tabla 'Users'
     INSERT INTO Users (username, password) VALUES ('admin', '12345');

     -- Insertar un dato en la tabla 'Biorreactor'
     INSERT INTO Biorreactor (nombre, fecha_creacion, FK_user_id)
     VALUES ('Biorreactor1', '2023-11-14', 1);


     -- Insertar 10 datos para el bucle pH
     INSERT INTO Loops (loop_id, loopName, setpoint, processValue, FK_biorreactor_id, timestamp)
     VALUES
     (1, 'pH', 7.0, 7.2, 1, '2023-11-14 12:00:00'),
     (2, 'pH', 7.2, 7.4, 1, '2023-11-14 12:02:00'),
     (3, 'pH', 7.1, 7.3, 1, '2023-11-14 12:04:00'),
     (4, 'pH', 7.3, 7.5, 1, '2023-11-14 12:06:00'),
     (5, 'pH', 7.2, 7.4, 1, '2023-11-14 12:08:00'),
     (6, 'pH', 7.4, 7.6, 1, '2023-11-14 12:10:00'),
     (7, 'pH', 7.3, 7.5, 1, '2023-11-14 12:12:00'),
     (8, 'pH', 7.5, 7.7, 1, '2023-11-14 12:14:00'),
     (9, 'pH', 7.4, 7.6, 1, '2023-11-14 12:16:00'),
     (10, 'pH', 7.6, 7.8, 1, '2023-11-14 12:18:00');

     -- Insertar 10 datos para el bucle Temperature
     INSERT INTO Loops (loop_id, loopName, setpoint, processValue, FK_biorreactor_id, timestamp)
     VALUES
     (11, 'Temperature', 25.0, 25.5, 1, '2023-11-14 12:00:00'),
     (12, 'Temperature', 25.5, 26.0, 1, '2023-11-14 12:02:00'),
     (13, 'Temperature', 25.2, 25.7, 1, '2023-11-14 12:04:00'),
     (14, 'Temperature', 25.7, 26.2, 1, '2023-11-14 12:06:00'),
     (15, 'Temperature', 25.4, 25.9, 1, '2023-11-14 12:08:00'),
     (16, 'Temperature', 25.9, 26.4, 1, '2023-11-14 12:10:00'),
     (17, 'Temperature', 25.6, 26.1, 1, '2023-11-14 12:12:00'),
     (18, 'Temperature', 26.1, 26.6, 1, '2023-11-14 12:14:00'),
     (19, 'Temperature', 25.8, 26.3, 1, '2023-11-14 12:16:00'),
     (20, 'Temperature', 26.3, 26.8, 1, '2023-11-14 12:18:00');

     -- Insertar 10 datos para el bucle DO
     INSERT INTO Loops (loop_id, loopName, setpoint, processValue, FK_biorreactor_id, timestamp)
     VALUES
     (21, 'DO', 5.0, 5.2, 1, '2023-11-14 12:00:00'),
     (22, 'DO', 5.2, 5.5, 1, '2023-11-14 12:02:00'),
     (23, 'DO', 4.8, 5.0, 1, '2023-11-14 12:04:00'),
     (24, 'DO', 5.3, 5.5, 1, '2023-11-14 12:06:00'),
     (25, 'DO', 5.1, 5.3, 1, '2023-11-14 12:08:00'),
     (26, 'DO', 5.4, 5.6, 1, '2023-11-14 12:10:00'),
     (27, 'DO', 5.2, 5.4, 1, '2023-11-14 12:12:00'),
     (28, 'DO', 5.5, 5.7, 1, '2023-11-14 12:14:00'),
     (29, 'DO', 5.3, 5.5, 1, '2023-11-14 12:16:00'),
     (30, 'DO', 5.6, 5.8, 1, '2023-11-14 12:18:00');

     -- Insertar 10 datos para el bucle Stirring Rate
     INSERT INTO Loops (loop_id, loopName, setpoint, processValue, FK_biorreactor_id, timestamp)
     VALUES
     (31, 'Stirring Rate', 300, 310, 1, '2023-11-14 12:00:00'),
     (32, 'Stirring Rate', 310, 320, 1, '2023-11-14 12:02:00'),
     (33, 'Stirring Rate', 305, 315, 1, '2023-11-14 12:04:00'),
     (34, 'Stirring Rate', 315, 325, 1, '2023-11-14 12:06:00'),
     (35, 'Stirring Rate', 310, 320, 1, '2023-11-14 12:08:00'),
     (36, 'Stirring Rate', 320, 330, 1, '2023-11-14 12:10:00'),
     (37, 'Stirring Rate', 315, 325, 1, '2023-11-14 12:12:00'),
     (38, 'Stirring Rate', 325, 335, 1, '2023-11-14 12:14:00'),
     (39, 'Stirring Rate', 320, 330, 1, '2023-11-14 12:16:00'),
     (40, 'Stirring Rate', 330, 340, 1, '2023-11-14 12:18:00');

     ***/

    /***

     INSERT INTO Loops (loop_id, loopName, setpoint, processValue, FK_biorreactor_id, timestamp)
     VALUES
     (45, 'pH', 7.1, 8.3, 1, '2023-11-14 12:20:00'),
     (46, 'Temperature', 26.3, 27.2, 1, '2023-11-14 12:20:00'),
     (47, 'DO', 5.9, 6.2, 1, '2023-11-14 12:20:00'),
     (48, 'Stirring Rate', 340, 390, 1, '2023-11-14 12:20:00')

     ***/
}