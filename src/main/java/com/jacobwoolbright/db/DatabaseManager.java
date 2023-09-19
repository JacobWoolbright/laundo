package com.jacobwoolbright.db;

import com.jacobwoolbright.Status;

import java.sql.*;
import java.util.ArrayList;
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

    public Map<java.util.Date, Integer> getWasherAvailabilityBy5Minutes() {
        Map<java.util.Date, Integer> availabilityMap = new HashMap<>();

        String query = "SELECT FROM_UNIXTIME(FLOOR(UNIX_TIMESTAMP(`time`) / 300) * 300 + 150) AS average_time, " +
                "SUM(`available`) AS total_available " +
                "FROM machines " +
                "WHERE `machineID` <= 100 " +
                "AND `time` >= NOW() - INTERVAL 1 DAY " +
                "GROUP BY average_time";


        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Timestamp averageTime = resultSet.getTimestamp("average_time");
                java.util.Date averageTimeJava = new java.util.Date(averageTime.getTime());
                int totalAvailable = resultSet.getInt("total_available");
                availabilityMap.put(averageTimeJava, totalAvailable);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }

        return availabilityMap;
    }

    public Map<java.util.Date, Integer> getDryerAvailabilityBy5Minutes() {
        Map<java.util.Date, Integer> availabilityMap = new HashMap<>();

        String query = "SELECT FROM_UNIXTIME(FLOOR(UNIX_TIMESTAMP(`time`) / 300) * 300 + 150) AS average_time, " +
                "SUM(`available`) AS total_available " +
                "FROM machines " +
                "WHERE `machineID` >= 101 " +
                "AND `time` >= NOW() - INTERVAL 1 DAY " +
                "GROUP BY average_time";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Timestamp averageTime = resultSet.getTimestamp("average_time");
                java.util.Date averageTimeJava = new java.util.Date(averageTime.getTime());
                int totalAvailable = resultSet.getInt("total_available");
                availabilityMap.put(averageTimeJava, totalAvailable);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception as needed
        }

        return availabilityMap;
    }

    public Map<java.util.Date, Integer> getDryerAvailabilityByTime(String timeString) {
        Map<java.util.Date, Integer> availabilityMap = new HashMap<>();

        String timeValue = timeString.substring(0, timeString.length() - 1);
        String timeUnit = timeString.substring(timeString.length() - 1);

        int interval;
        switch (timeUnit) {
            case "m":
                interval = Integer.parseInt(timeValue);
                break;
            case "h":
                interval = Integer.parseInt(timeValue) * 60;
                break;
            case "d":
                interval = Integer.parseInt(timeValue) * 24 * 60;
                break;
            default:
                throw new IllegalArgumentException("Invalid time unit: " + timeUnit);
        }

        String query = "SELECT FROM_UNIXTIME(FLOOR(UNIX_TIMESTAMP(`time`) / 300) * 300 + 150) AS average_time, " +
                "SUM(`available`) AS total_available " +
                "FROM machines " +
                "WHERE `machineID` >= 101 " +
                "AND 'time' >= NOW() - INTERVAL ? SECOND " +
                "GROUP BY average_time";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, interval * Integer.parseInt(timeValue));

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Timestamp averageTime = resultSet.getTimestamp("average_time");
                    java.util.Date averageTimeJava = new java.util.Date(averageTime.getTime());
                    int totalAvailable = resultSet.getInt("total_available");
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
