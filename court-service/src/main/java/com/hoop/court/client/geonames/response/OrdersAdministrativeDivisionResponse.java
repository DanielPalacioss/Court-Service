package com.hoop.court.client.geonames.response;

import com.hoop.court.model.OrdersAdministrativeDivision;
import lombok.Data;

import java.util.List;

@Data
public class OrdersAdministrativeDivisionResponse {
    private long totalResultsCount;
    private List<OrdersAdministrativeDivision> geonames;
}
