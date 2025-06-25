package com.hoop.court.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrdersAdministrativeDivision {

    private String adminCode1;
    private String lng;
    private long geonameId;
    private String toponymName;
    private String countryId;
    private String fcl;
    private long population;
    private String countryCode;
    private String name;
    private String fclName;
    private AdminCodes1 adminCodes1;
    private String countryName;
    private String fcodeName;
    private String adminName1;
    private String lat;
    private String fcode;

    public static record AdminCodes1(@JsonProperty("ISO3166_2") String ISO3166_2){}
}