package com.jacobwoolbright.db;

import com.jacobwoolbright.Status;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DatabaseManager {

    private static DatabaseManager instance;

    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/laundo"; // Replace with your database URL
    private static final String username = "root";
    private static final String password = "Mercy0504";

    private Connection connection;

    private DatabaseManager(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void checkAndResetConnection(){
        try {
            if(connection.isValid(5)){
                return;
            }
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static DatabaseManager getInstance() {
        if(instance == null){
            instance = new DatabaseManager();
        }
        instance.checkAndResetConnection();
        return instance;
    }

    public void insertStatus(Status status) {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String query = "INSERT INTO machines (machineID, status, timeLeft, running, available) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, status.getMachineID());

                if (status.getStatusMessage() != null) {
                    statement.setString(2, status.getStatusMessage());
                } else {
                    statement.setNull(2, Types.VARCHAR);
                }


                statement.setInt(3, status.getTimeLeft());
                statement.setBoolean(4, status.isRunning());
                statement.setBoolean(5, status.isAvailable());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<java.util.Date, Integer> getDryerAvailabilityRaw() {
        Map<java.util.Date, Integer> availabilityMap = new HashMap<>();

        String query = "SELECT time, available\n" +
                "FROM machines\n" +
                "WHERE time >= NOW() - INTERVAL 1 DAY\n" +
                "AND machineID >= 101;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Timestamp captureTime = resultSet.getTimestamp("time");
                    java.util.Date averageTimeJava = new java.util.Date(captureTime.getTime());
                    int totalAvailable = resultSet.getInt("available");
                    availabilityMap.put(averageTimeJava, totalAvailable);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }

        return availabilityMap;
    }

    public Map<java.util.Date, Integer> getWasherAvailabilityRaw() {
        Map<java.util.Date, Integer> availabilityMap = new HashMap<>();

        String query = "SELECT time, available\n" +
                "FROM machines\n" +
                "WHERE time >= NOW() - INTERVAL 1 DAY\n" +
                "AND machineID <= 100;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Timestamp captureTime = resultSet.getTimestamp("time");
                    java.util.Date averageTimeJava = new java.util.Date(captureTime.getTime());
                    int totalAvailable = resultSet.getInt("available");
                    availabilityMap.put(averageTimeJava, totalAvailable);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }

        return availabilityMap;
    }

    public Map<java.util.Date, Integer> getDryerAvailabilityRaw(String timespan) {
        Map<java.util.Date, Integer> availabilityMap = new HashMap<>();

        String query = "";
        if(timespan.endsWith("d")){
            query = "SELECT time, available\n" +
                    "FROM machines\n" +
                    "WHERE time >= NOW() - INTERVAL " + timespan.substring(0,timespan.length()-1) + " DAY\n" +
                    "AND machineID >= 101;";
        } else if (timespan.endsWith("h")) {
            query = "SELECT time, available\n" +
                    "FROM machines\n" +
                    "WHERE time >= NOW() - INTERVAL " + timespan.substring(0,timespan.length()-1) + " HOUR\n" +
                    "AND machineID >= 101;";
        }
        else if (timespan.endsWith("m")) {
            query = "SELECT time, available\n" +
                    "FROM machines\n" +
                    "WHERE time >= NOW() - INTERVAL " + timespan.substring(0,timespan.length()-1) + " MINUTE\n" +
                    "AND machineID >= 101;";
        }

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Timestamp captureTime = resultSet.getTimestamp("time");
                    java.util.Date averageTimeJava = new java.util.Date(captureTime.getTime());
                    int totalAvailable = resultSet.getInt("available");
                    availabilityMap.put(averageTimeJava, totalAvailable);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }


        return availabilityMap;
    }

    public Map<java.util.Date, Integer> getWasherAvailabilityRaw(String timespan) {
        Map<java.util.Date, Integer> availabilityMap = new HashMap<>();

        String query = "";
        if(timespan.endsWith("d")){
            query = "SELECT time, available\n" +
                    "FROM machines\n" +
                    "WHERE time >= NOW() - INTERVAL " + timespan.substring(0,timespan.length()-1) + " DAY\n" +
                    "AND machineID <= 100;";
        } else if (timespan.endsWith("h")) {
            query = "SELECT time, available\n" +
                    "FROM machines\n" +
                    "WHERE time >= NOW() - INTERVAL " + timespan.substring(0,timespan.length()-1) + " HOUR\n" +
                    "AND machineID <= 100;";
        }
        else if (timespan.endsWith("m")) {
            query = "SELECT time, available\n" +
                    "FROM machines\n" +
                    "WHERE time >= NOW() - INTERVAL " + timespan.substring(0,timespan.length()-1) + " MINUTE\n" +
                    "AND machineID <= 100;";
        }

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Timestamp captureTime = resultSet.getTimestamp("time");
                    java.util.Date averageTimeJava = new java.util.Date(captureTime.getTime());
                    int totalAvailable = resultSet.getInt("available");
                    availabilityMap.put(averageTimeJava, totalAvailable);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }


        return availabilityMap;
    }
}
