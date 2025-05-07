package com.lab8;

import java.util.List;

import com.lab8.jpa.PersistenceManager;
import com.lab8.model.City;
import com.lab8.repository.CityRepository;

public class JpaTest {
    public static void main(String[] args) {
        try {
            CityRepository cityRepo = new CityRepository();

            City newCity = new City();
            newCity.setName("Test City");
            newCity.setCountryId(1);
            newCity.setCapital(false);
            newCity.setLatitude(45.0);
            newCity.setLongitude(25.0);

            cityRepo.create(newCity);
            System.out.println("new city");

            City foundCity = cityRepo.findById(1);
            if (foundCity != null) {
                System.out.println("Found city by ID: " + foundCity.getName());
            }

            List<City> cities = cityRepo.findByName("Test");
            System.out.println("Found " + cities.size() + " cities matching 'Test'");
            for (City city : cities) {
                System.out.println("- " + city.getName());
            }

        } finally {
            PersistenceManager.getInstance().closeEntityManagerFactory();
        }
    }
}