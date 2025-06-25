package com.hoop.court.service;

import com.hoop.court.dto.OrdersAdministrativeDivisionDTO;
import com.hoop.court.error.RequestException;
import com.hoop.court.repository.OrdersAdministrativeDivisionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrdersAdministrativeDivisionServiceImp implements OrdersAdministrativeDivisionService {

    private final OrdersAdministrativeDivisionRepository ordersAdministrativeDivisionRepository;
    private final MongoTemplate mongoTemplate;


    @Override
    public List<OrdersAdministrativeDivisionDTO> getAllFirstOrderOfCountry(String countryId) {
        return ordersAdministrativeDivisionRepository
                .findByCountryIdAndFcodeName(countryId, "first-order administrative division").
                orElseThrow(() -> new RequestException("There is no a country with id "+ countryId, "400-Bad Request"));
    }

    @Override
    public List<OrdersAdministrativeDivisionDTO> getAllSecondOrderOfFirstOrder(String firstOrderName, String countryId) {
        return ordersAdministrativeDivisionRepository
                .findByAdminName1AndFcodeNameAndCountryId(firstOrderName, "second-order administrative division", countryId)
                .orElseThrow(() -> new RequestException("There is no first order administrative with name "+firstOrderName, "400-Bad Request"));
    }

}
