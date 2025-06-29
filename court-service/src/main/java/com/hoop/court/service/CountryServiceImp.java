package com.hoop.court.service;

import com.hoop.court.exception.RequestException;
import com.hoop.court.model.Country;
import com.hoop.court.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryServiceImp implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public List<Country> getCountries() {
        List<Country> countries = countryRepository.findAll();
        if(countries.isEmpty()){
            throw new RequestException("There are no countries in the database.","400-Bad Request");
        }
        return countries;
    }

    @Override
    public Country getCountry(long id) {
        return countryRepository.findById(id).orElseThrow(() -> new RequestException("There is no country with id "+id,"400-Bad Request"));
    }

    @Override
    public Country getCountry(String name) {
        return countryRepository.findByCountryName(name).orElseThrow(() -> new RequestException("There is no country with name "+name,"400-Bad Request"));
    }

}
