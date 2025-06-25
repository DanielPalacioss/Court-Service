package com.hoop.court.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "country")
@Data
public class Country {

    private String continent;
    private String capital;
    private String languages;
    @Id
    private long geonameId;
    private double south;
    private String isoAlpha3;
    private double north;
    private String fipsCode;
    private String population;
    private double east;
    private String isoNumeric;
    private String areaInSqKm;
    private String countryCode;
    private double west;
    private String countryName;
    private String postalCodeFormat;
    private String continentName;
    private String currencyCode;
}
