package com.lab8;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.lab8.model.City;
import com.lab8.util.DataImporter;
import com.lab8.util.GFG;
import com.opencsv.exceptions.CsvValidationException;

public class Main {
    public static void main(String args[]) {
        try {
            Connection conn = null;
            try {
                conn = Database.getConnection();

                importData();

                distanceCalculations();

            } catch (SQLException e) {
                System.err.println("database error " + e.getMessage());
            } finally {
                if (conn != null) {
                    Database.closeConnection(conn);
                }
            }

            Database.shutdown();

        } catch (Exception e) {
            System.err.println("error " + e.getMessage());
        }
    }

    private static void importData() {
        try {

            DataImporter importer = new DataImporter();
            importer.importCapitalsFromCSV("C:\\Users\\simon\\Desktop\\javagit\\lab8\\concap.csv");
            System.out.println("succes import");

        } catch (IOException e) {
            System.err.println("error read " + e.getMessage());
        } catch (CsvValidationException e) {
            System.err.println("error parsing " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("error database " + e.getMessage());
        }
    }

    private static void distanceCalculations() {
        try {

            CityDAO cityDAO = new CityDAO();
            List<City> capitals = cityDAO.findCapitals();

            if (capitals.size() >= 2) {
                java.util.Random random = new java.util.Random();
                int index1 = random.nextInt(capitals.size());

                int index2;
                do {
                    index2 = random.nextInt(capitals.size());
                } while (index2 == index1);

                City city1 = capitals.get(index1);
                City city2 = capitals.get(index2);

                double distance = GFG.distance(
                        city1.getLatitude(), city1.getLongitude(),
                        city2.getLatitude(), city2.getLongitude());

                System.out.printf("distance between %s and %s: %.2f km\n",
                        city1.getName(), city2.getName(), distance);

            } else {
                System.out.println("don t have 2 capitals");
            }

        } catch (SQLException e) {
            System.err.println("sql " + e.getMessage());
        }
    }
}