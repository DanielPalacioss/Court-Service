package com.hoop.court.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CourtRequestDTO {

    private List<CourtRequest> places;

    public static record CourtRequest(String id,
                                      Location location,
                                      String shortFormattedAddress,
                                      List<Photos> photos,
                                      PostalAddress postalAddress,
                                      AddressDescriptor addressDescriptor,
                                      GoogleMapsLinks googleMapsLinks){}

    public static record Location(double latitude, double longitude) {}
    public static record Photos(String googleMapsUri){}
    public static record PostalAddress(String locality, String administrativeArea){}

    public static record GoogleMapsLinks(String directionsUri, String placeUri){}

    // Record anidados para obtener el nombre de la cancha
    public static record AddressDescriptor(List<Landmarks> landmarks){}
    public static record Landmarks(DisplayName displayName){}
    public static record DisplayName(String text){}



}
