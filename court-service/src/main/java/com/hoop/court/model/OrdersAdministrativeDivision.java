package com.hoop.court.model;

import com.hoop.court.controller.OrdersAdministrativeDivisionDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "orders_administrative_division")
@Data
@NoArgsConstructor
public class OrdersAdministrativeDivision {

    private String lng;
    @Id
    private long geonameId;
    private String toponymName;
    private String countryId;
    private String fcl;
    private long population;
    private String countryCode;
    private String name;
    private String countryName;
    private String fcodeName;
    private String adminName1;
    private String lat;
    private String fcode;

    public OrdersAdministrativeDivision(OrdersAdministrativeDivisionDTO ordersAdministrativeDivisionDTO) {
        this.lng = ordersAdministrativeDivisionDTO.getLng();
        this.geonameId = ordersAdministrativeDivisionDTO.getGeonameId();
        this.toponymName = ordersAdministrativeDivisionDTO.getToponymName();
        this.countryId = ordersAdministrativeDivisionDTO.getCountryId();
        this.fcl = ordersAdministrativeDivisionDTO.getFcl();
        this.population = ordersAdministrativeDivisionDTO.getPopulation();
        this.countryCode = ordersAdministrativeDivisionDTO.getCountryCode();
        this.name = ordersAdministrativeDivisionDTO.getName().replace(" Department", "");
        this.countryName = ordersAdministrativeDivisionDTO.getCountryName();
        this.fcodeName = ordersAdministrativeDivisionDTO.getFcodeName();
        this.adminName1 = ordersAdministrativeDivisionDTO.getAdminName1();
        this.lat = ordersAdministrativeDivisionDTO.getLat();
        this.fcode = ordersAdministrativeDivisionDTO.getFcode();
    }
}
