package com.lab8.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lab8.CityDAO;
import com.lab8.ContinentDAO;
import com.lab8.CountryDAO;
import com.lab8.model.City;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class DataImporter {

    private final CountryDAO countryDAO;
    private final CityDAO cityDAO;
    private final ContinentDAO continentDAO;

    public DataImporter() {
        this.countryDAO = new CountryDAO();
        this.cityDAO = new CityDAO();
        this.continentDAO = new ContinentDAO();
    }

    public void importCapitalsFromCSV(String filePath) throws IOException, CsvValidationException, SQLException {

        List<City> citiesToImport = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            reader.readNext();

            String[] line;
            int lineCount = 1;
            while ((line = reader.readNext()) != null) {
                lineCount++;
                try {
                    processRow(line, citiesToImport);
                } catch (SQLException e) {
                    System.err.println("error at line " + lineCount + ": " + e.getMessage());
                }
            }
        }

        if (!citiesToImport.isEmpty()) {
            cityDAO.batchCreate(citiesToImport);
            System.out.println("succes import");
        }
    }

    private void processRow(String[] line, List<City> cities) throws SQLException {
        if (line == null || line.length < 6) {
            System.out.println("skipped line");
            return;
        }

        String countryName = line[0];
        String capitalName = line[1];
        String continentName = line[5];
        Double latitude;
        Double longitude;

        try {
            latitude = Double.valueOf(line[2]);
            longitude = Double.valueOf(line[3]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid coordinates for " + capitalName + ", skipping");
            return;
        }

        Integer continentId = continentDAO.findByName(continentName);
        if (continentId == null) {
            continentDAO.create(continentName);
            continentId = continentDAO.findByName(continentName);
        }

        Integer countryId = countryDAO.findByName(countryName);
        if (countryId == null) {
            countryDAO.create(countryName, continentId);
            countryId = countryDAO.findByName(countryName);
        }

        City city = new City(capitalName, countryId, true, latitude, longitude);
        cities.add(city);
    }
}