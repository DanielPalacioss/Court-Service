package com.hoop.court.controller;

import com.hoop.court.client.geonames.GeoNamesClient;
import com.hoop.court.model.Country;
import com.hoop.court.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("geo/country")
@CrossOrigin(origins = "http://localhost:8080")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private GeoNamesClient geoNamesClient;

    @GetMapping("/all")
    public ResponseEntity<List<Country>> getCountries() {
        return ResponseEntity.ok(countryService.getCountries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountry(@PathVariable long id) {
        return ResponseEntity.ok(countryService.getCountry(id));
    }

    @GetMapping()
    public ResponseEntity<Country> getCountry(@RequestParam String name) {
        return ResponseEntity.ok(countryService.getCountry(name));
    }

    @PostMapping()
    public ResponseEntity<?> saveCountries() {
        geoNamesClient.addCountries();
        return ResponseEntity.status(HttpStatus.CREATED).body("The countries have been added successfully.");
    }
}
