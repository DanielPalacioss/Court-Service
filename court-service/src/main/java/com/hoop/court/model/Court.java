package com.hoop.court.model;

import com.hoop.court.dto.CourtRequestDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(value = "court")
@Data
@NoArgsConstructor
public class Court {
    @Id
    private String courtGmaps;
    private String name;
    private String alternativeName;
    private double latitude;
    private double longitude;
    private String typeCourt;
    private String address;
    private String cityCourt;
    private String firstOrderName;
    private String howToGetThere;
    private String direction;
    private List<String> courtImages;
    private long cityGeonameId;

    public Court(CourtRequestDTO.CourtRequest court, long cityGeonameId) {
        this.courtGmaps = court.id();
        this.name = court.addressDescriptor().landmarks().get(0).displayName().text();
        this.alternativeName = court.addressDescriptor().landmarks().get(1).displayName().text();
        this.latitude = court.location().latitude();
        this.longitude = court.location().longitude();
        this.typeCourt = "5x5";
        this.address = court.shortFormattedAddress();
        this.cityCourt = court.postalAddress().locality();
        this.firstOrderName = court.postalAddress().administrativeArea();
        this.howToGetThere = court.googleMapsLinks().directionsUri();
        this.direction = court.googleMapsLinks().placeUri();
        this.courtImages = new ArrayList<>();
        if (court.photos() != null && !court.photos().isEmpty()) {
            court.photos().forEach(photo -> this.courtImages.add(photo.googleMapsUri()));
        }
        this.cityGeonameId = cityGeonameId;
    }
}
