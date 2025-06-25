package com.hoop.court.service;

import com.hoop.court.model.Country;

import java.util.List;

public interface CountryService {

    List<Country> getCountries();
    Country getCountry(long id);
    Country getCountry(String name);
}
