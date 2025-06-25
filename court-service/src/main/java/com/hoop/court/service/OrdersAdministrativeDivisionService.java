package com.hoop.court.service;

import com.hoop.court.dto.OrdersAdministrativeDivisionDTO;

import java.util.List;

public interface OrdersAdministrativeDivisionService {
    List<OrdersAdministrativeDivisionDTO> getAllFirstOrderOfCountry(String countryId);
    List<OrdersAdministrativeDivisionDTO> getAllSecondOrderOfFirstOrder(String FirstOrderName, String countryId);
}
