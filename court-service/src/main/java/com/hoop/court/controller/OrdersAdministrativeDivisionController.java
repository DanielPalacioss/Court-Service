package com.hoop.court.controller;

import com.hoop.court.client.geonames.GeoNamesClient;
import com.hoop.court.model.OrdersAdministrativeDivision;
import com.hoop.court.service.CountryService;
import com.hoop.court.service.OrdersAdministrativeDivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("geo/ordersAdministrative")
@CrossOrigin(origins = "http://localhost:8080")
public class OrdersAdministrativeDivisionController {

    @Autowired
    private OrdersAdministrativeDivisionService ordersAdministrativeDivisionService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private GeoNamesClient geoNamesClient;

    @GetMapping("/firstOrderOfCountry")
    public ResponseEntity<List<OrdersAdministrativeDivision>> getAllFirstOrderOfCountry(@RequestParam String countryId) {
        return ResponseEntity.ok(ordersAdministrativeDivisionService.getAllFirstOrderOfCountry(countryId));
    }

    @GetMapping("/secondOrderOfCountry")
    public ResponseEntity<List<OrdersAdministrativeDivision>> getAllSecondOrderOfFirstOrder(@RequestParam String firstOrderName, @RequestParam String countryId) {
        return ResponseEntity.ok(ordersAdministrativeDivisionService.getAllSecondOrderOfFirstOrder(firstOrderName, countryId));
    }

    @PostMapping()
    public ResponseEntity<?> saveAllFirstAndSecondOrder()
    {
        geoNamesClient.addOrders(countryService.getCountries());
        return ResponseEntity.status(HttpStatus.CREATED).body("The Orders Administrative Division have been added successfully.");
    }

}
