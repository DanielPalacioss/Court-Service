package com.hoop.court.dto;

import com.hoop.court.model.OrdersAdministrativeDivision;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "orders_administrative_division")
@Data
@NoArgsConstructor
public class OrdersAdministrativeDivisionDTO {

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

    public OrdersAdministrativeDivisionDTO(OrdersAdministrativeDivision ordersAdministrativeDivision) {
        this.lng = ordersAdministrativeDivision.getLng();
        this.geonameId = ordersAdministrativeDivision.getGeonameId();
        this.toponymName = ordersAdministrativeDivision.getToponymName();
        this.countryId = ordersAdministrativeDivision.getCountryId();
        this.fcl = ordersAdministrativeDivision.getFcl();
        this.population = ordersAdministrativeDivision.getPopulation();
        this.countryCode = ordersAdministrativeDivision.getCountryCode();
        this.name = ordersAdministrativeDivision.getName().replace(" Department", "");
        this.countryName = ordersAdministrativeDivision.getCountryName();
        this.fcodeName = ordersAdministrativeDivision.getFcodeName();
        this.adminName1 = ordersAdministrativeDivision.getAdminName1();
        this.lat = ordersAdministrativeDivision.getLat();
        this.fcode = ordersAdministrativeDivision.getFcode();
    }
}
