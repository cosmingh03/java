package com.lab8;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO {
    public void create(String name, int continentId) throws SQLException {
        try (Connection con = Database.getConnection();
                PreparedStatement pstmt = con.prepareStatement(
                        "INSERT INTO countries (name, continent) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, continentId);
            pstmt.executeUpdate();
            con.commit();
        }
    }

    public void create(String name, String code, int continentId) throws SQLException {
        try (Connection con = Database.getConnection();
                PreparedStatement pstmt = con.prepareStatement(
                        "INSERT INTO countries (name, code, continent) VALUES (?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.setString(2, code);
            pstmt.setInt(3, continentId);
            pstmt.executeUpdate();
            con.commit();
        }
    }

    public Integer findByName(String name) throws SQLException {
        try (Connection con = Database.getConnection();
                PreparedStatement pstmt = con.prepareStatement("SELECT id FROM countries WHERE name=?")) {
            pstmt.setString(1, name); // Set parameter first
            try (ResultSet rs = pstmt.executeQuery()) { // Then execute query
                return rs.next() ? rs.getInt(1) : null;
            }
        }
    }

    public String findById(int id) throws SQLException {
        try (Connection con = Database.getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(
                        "select name from countries where id=" + id)) {
            return rs.next() ? rs.getString("name") : null;
        }
    }

    public List<String> findCountriesByContinent(int continentId) throws SQLException {
        List<String> countries = new ArrayList<>();
        try (Connection con = Database.getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(
                        "select name from countries where continent=" + continentId)) {
            while (rs.next()) {
                countries.add(rs.getString("name"));
            }
            return countries;
        }
    }
}
