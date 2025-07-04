package com.hoop.court.service;

import com.hoop.court.model.OrdersAdministrativeDivision;
import com.hoop.court.exception.RequestException;
import com.hoop.court.repository.OrdersAdministrativeDivisionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrdersAdministrativeDivisionServiceImp implements OrdersAdministrativeDivisionService {

    private final OrdersAdministrativeDivisionRepository ordersAdministrativeDivisionRepository;

    @Override
    public List<OrdersAdministrativeDivision> getAllFirstOrderOfCountry(String countryId) {
        List<OrdersAdministrativeDivision> ordersAdministrativeDivisions = ordersAdministrativeDivisionRepository
                .findByCountryIdAndFcodeName(countryId, "first-order administrative division");
        if(ordersAdministrativeDivisions.isEmpty()){
            throw new RequestException("There is no a country with id "+ countryId, "400-Bad Request");
        }
        return ordersAdministrativeDivisions;
    }

    @Override
    public List<OrdersAdministrativeDivision> getAllSecondOrderOfFirstOrder(String firstOrderName, String countryId) {
        List<OrdersAdministrativeDivision> ordersAdministrativeDivisions = ordersAdministrativeDivisionRepository
                .findByAdminName1AndFcodeNameAndCountryId(firstOrderName, "second-order administrative division", countryId);
        if(ordersAdministrativeDivisions.isEmpty()){
            throw new RequestException("There is no first order administrative with name "+firstOrderName, "400-Bad Request");
        }
        return ordersAdministrativeDivisions;
    }

}
