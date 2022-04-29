package mk.ukim.finki.wp.service.impl;

import mk.ukim.finki.wp.model.City;
import mk.ukim.finki.wp.model.exceptions.InvalidCityIdException;
import mk.ukim.finki.wp.repository.CityRepository;
import mk.ukim.finki.wp.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City findById(Long id) {
        return this.cityRepository.findById(id).orElseThrow(InvalidCityIdException::new);
    }

    @Override
    public List<City> listAll() {
        return this.cityRepository.findAll();
    }

    @Override
    public City create(String name) {
        return this.cityRepository.save(new City(name));
    }
}
