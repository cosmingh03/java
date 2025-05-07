package com.lab8;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lab8.model.City;

public class CityDAO {

    public void create(City city) throws SQLException {
        String sql = "INSERT INTO cities (name, country, capital, latitude, longitude) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, city.getName());
            pstmt.setInt(2, city.getCountryId());
            pstmt.setBoolean(3, city.isCapital());
            pstmt.setDouble(4, city.getLatitude());
            pstmt.setDouble(5, city.getLongitude());

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    city.setId(rs.getInt(1));
                }
            }

            conn.commit();
        }
    }

    public void batchCreate(List<City> cities) throws SQLException {
        String sql = "INSERT INTO cities (name, country, capital, latitude, longitude) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            for (City city : cities) {
                pstmt.setString(1, city.getName());
                pstmt.setInt(2, city.getCountryId());
                pstmt.setBoolean(3, city.isCapital());
                pstmt.setDouble(4, city.getLatitude());
                pstmt.setDouble(5, city.getLongitude());
                pstmt.addBatch();
            }

            pstmt.executeBatch();
            conn.commit();
        }
    }

    public City findById(int id) throws SQLException {
        String sql = "SELECT * FROM cities WHERE id = ?";

        try (Connection conn = Database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            pstmt.setInt(1, id);

            if (rs.next()) {
                return mapResultSetToCity(rs);
            }
        }

        return null;
    }

    public List<City> findByCountryId(int countryId) throws SQLException {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT * FROM cities WHERE country = ?";

        try (Connection conn = Database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            pstmt.setInt(1, countryId);

            while (rs.next()) {
                cities.add(mapResultSetToCity(rs));
            }
        }

        return cities;
    }

    public List<City> findCapitals() throws SQLException {
        List<City> capitals = new ArrayList<>();
        String sql = "SELECT c.*, co.name as country_name FROM cities c JOIN countries co ON c.country = co.id WHERE c.capital = true";

        try (Connection conn = Database.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                capitals.add(mapResultSetToCity(rs));
            }
        }

        return capitals;
    }

    private City mapResultSetToCity(ResultSet rs) throws SQLException {
        City city = new City();
        city.setId(rs.getInt("id"));
        city.setName(rs.getString("name"));
        city.setCountryId(rs.getInt("country"));
        city.setCapital(rs.getBoolean("capital"));
        city.setLatitude(rs.getDouble("latitude"));
        city.setLongitude(rs.getDouble("longitude"));
        return city;
    }
}