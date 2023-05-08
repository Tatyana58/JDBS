package Hibernate.service;

import Hibernate.model.City;

import java.util.List;

public interface CityDao {
    public City addCity(City city);

    City getById(int id);

    List<City> getAllCity();

    City updateCity(City city);

    City deleteCity(City city);
}