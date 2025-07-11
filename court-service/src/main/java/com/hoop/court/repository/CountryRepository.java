package com.hoop.court.repository;

import com.hoop.court.model.Country;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends MongoRepository<Country,Long> {

    Optional<Country> findByCountryName(String countryName);
}
