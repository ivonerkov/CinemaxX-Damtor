package mk.ukim.finki.wp.service;

import mk.ukim.finki.wp.model.City;
import mk.ukim.finki.wp.model.Location;
import mk.ukim.finki.wp.model.Movie;

import java.util.List;

public interface LocationService {
    List<Location> listAllLocations();
    Location findById(Long id);
    Location create(String name, String description, City city, List<Long> movies);
    Location update(Long id, String name, String description, Long city, List<Long> movies);
    Location delete(Long id);
}
