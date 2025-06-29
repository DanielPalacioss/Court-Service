package com.hoop.court.service;

import com.hoop.court.model.OrdersAdministrativeDivision;

import java.util.List;

public interface OrdersAdministrativeDivisionService {
    List<OrdersAdministrativeDivision> getAllFirstOrderOfCountry(String countryId);
    List<OrdersAdministrativeDivision> getAllSecondOrderOfFirstOrder(String FirstOrderName, String countryId);
}
