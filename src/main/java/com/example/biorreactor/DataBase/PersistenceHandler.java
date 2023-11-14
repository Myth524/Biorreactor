package com.example.biorreactor.DataBase;

import java.sql.*;

public class PersistenceHandler implements IPersistenceHandler {
    private static PersistenceHandler instance;
    private String url = "localhost";
    private int port = 5432;
    private String databaseName = "Biorreactor";
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

    /***
     CREATE TABLE users (
     username VARCHAR(50) PRIMARY KEY,
     password VARCHAR(255) NOT NULL
     );
     INSERT INTO users (username, password) VALUES ('admin', '12345');
     ***/
}