package mk.ukim.finki.wp.service;

import mk.ukim.finki.wp.model.City;

import java.util.List;

public interface CityService {
    City findById(Long id);
    List<City> listAll();
    City create(String name);
}
