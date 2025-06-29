package com.hoop.court.client.geonames.response;

import com.hoop.court.controller.OrdersAdministrativeDivisionDTO;
import lombok.Data;

import java.util.List;

@Data
public class OrdersAdministrativeDivisionResponse {
    private long totalResultsCount;
    private List<OrdersAdministrativeDivisionDTO> geonames;
}
