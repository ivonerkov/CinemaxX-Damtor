package mk.ukim.finki.wp.service.impl;

import mk.ukim.finki.wp.model.City;
import mk.ukim.finki.wp.model.Location;
import mk.ukim.finki.wp.model.Movie;
import mk.ukim.finki.wp.model.exceptions.InvalidCityIdException;
import mk.ukim.finki.wp.model.exceptions.InvalidLocationIdException;
import mk.ukim.finki.wp.repository.CityRepository;
import mk.ukim.finki.wp.repository.LocationRepository;
import mk.ukim.finki.wp.repository.MovieRepository;
import mk.ukim.finki.wp.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final CityRepository cityRepository;
    private final MovieRepository movieRepository;

    public LocationServiceImpl(LocationRepository locationRepository, CityRepository cityRepository, MovieRepository movieRepository) {
        this.locationRepository = locationRepository;
        this.cityRepository = cityRepository;
        this.movieRepository = movieRepository;
    }


    @Override
    public List<Location> listAllLocations() {
        return this.locationRepository.findAll();
    }

    @Override
    public Location findById(Long id) {
        return this.locationRepository.findById(id).orElseThrow(InvalidLocationIdException::new);
    }

    @Override
    public Location create(String name, String description, City city, List<Long> movies) {
        return this.locationRepository.save(new Location(name,description, city, movieRepository.findAllById(movies)));
    }

    public Location update(Long id, String name, String description, Long city, List<Long> movies) {
        Location location = this.locationRepository.findById(id).orElseThrow(InvalidLocationIdException::new);
        location.setName(name);
        location.setDescription(description);
        City city1 = this.cityRepository.findById(city).orElseThrow(InvalidCityIdException::new);
        location.setLocation(city1);
        location.setMovies(movieRepository.findAllById(movies));
        return this.locationRepository.save(location);
    }

    @Override
    public Location delete(Long id) {
        Location location = this.locationRepository.findById(id).orElseThrow(InvalidLocationIdException::new);
        this.locationRepository.delete(location);
        return location;
    }
}
