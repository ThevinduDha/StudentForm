package com.example.student_form;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBUtil {
    // Change these values according to your SQL Server setup
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=student;encrypt=true;trustServerCertificate=true";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "123";

    private static Connection connection;

    // Get connection (lazy initialized)
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                System.out.println("✅ Database connection established.");
            } catch (SQLException e) {
                System.err.println("❌ Failed to connect to database: " + e.getMessage());
                throw e;
            }
        }
        return connection;
    }

}