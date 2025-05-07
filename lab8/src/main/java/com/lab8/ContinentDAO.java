package com.lab8;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContinentDAO {
    public void create(String name) throws SQLException {
        try (Connection con = Database.getConnection();
                PreparedStatement pstmt = con.prepareStatement(
                        "insert into continents (name) values (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }

    public Integer findByName(String name) throws SQLException {
        try (Connection con = Database.getConnection();
                PreparedStatement stmt = con.prepareStatement(
                        "select id from continents where name=?")) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? rs.getInt(1) : null;
            }
        }
    }

    public String findById(int id) throws SQLException {
        try (Connection con = Database.getConnection();
                PreparedStatement stmt = con.prepareStatement(
                        "select name from continents where id=?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? rs.getString("name") : null;
            }
        }
    }
}
