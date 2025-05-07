package com.lab8;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Database {
    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/cities");
        config.setUsername("postgres");
        config.setPassword("password");
        config.setMaximumPoolSize(10);
        config.setAutoCommit(false);
        config.setPoolName("Cities-DB-Pool");

        dataSource = new HikariDataSource(config);
    }

    private Database() {
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    public static void rollback(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                System.err.println("Error rolling back: " + e.getMessage());
            }
        }
    }

    public static void shutdown() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
