package com.hoop.court.client.geonames;

import com.hoop.court.client.geonames.response.CountryResponse;
import com.hoop.court.client.geonames.response.OrdersAdministrativeDivisionResponse;
import com.hoop.court.model.OrdersAdministrativeDivision;
import com.hoop.court.exception.RequestException;
import com.hoop.court.model.Country;
import com.hoop.court.repository.CountryRepository;
import com.hoop.court.repository.OrdersAdministrativeDivisionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeoNamesClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final CountryRepository countryRepository;
    private final OrdersAdministrativeDivisionRepository ordersAdministrativeDivisionRepository;
    @Value("${user.geonames}")
    private String userGeonames;

    public GeoNamesClient(CountryRepository countryRepository, OrdersAdministrativeDivisionRepository ordersAdministrativeDivisionRepository) {
        this.countryRepository = countryRepository;
        this.ordersAdministrativeDivisionRepository = ordersAdministrativeDivisionRepository;
    }

    public void addCountries() {
        CountryResponse countryResponse = restTemplate.
                getForObject("http://api.geonames.org/countryInfoJSON?username="+userGeonames, CountryResponse.class);
        List<Country> countries = new ArrayList<>();
        if (countryResponse != null && countryResponse.getGeonames() != null) {
            countryResponse.getGeonames().forEach(country -> {
                if (countryRepository.findById(country.getGeonameId()).isEmpty()){
                    countries.add(country);
                }
            });
            countryRepository.saveAll(countries);
        }
        else  {
            throw new RequestException("Geonames response is empty","500-Error API GEONAMES");
        }
    }

    public void addOrders(List<Country> countries) {
        countries.forEach(country -> { // Iterate over all countries
            try {
                OrdersAdministrativeDivisionResponse firstOrdersAdministrativeDivisionResponse = restTemplate. // get firstOrders of country
                        getForObject("http://api.geonames.org/childrenJSON?geonameId=" + country.getGeonameId() + "&username="+userGeonames, OrdersAdministrativeDivisionResponse.class);
                if (firstOrdersAdministrativeDivisionResponse == null || firstOrdersAdministrativeDivisionResponse.getGeonames() == null) {
                    System.err.println("No data found for the country: " + country.getCountryName());
                    return; // Continue with next country
                }
                List<OrdersAdministrativeDivision> firstOrdersAdministrativeDivision = new ArrayList<>();

                firstOrdersAdministrativeDivisionResponse.getGeonames().forEach(firstOrder -> { // Iterate over all firstOrders
                    try {
                        if(ordersAdministrativeDivisionRepository.findById(firstOrder.getGeonameId()).isPresent()) return; // If the first order administrative is found, there are already second order administrative. Continue with next firstOrder
                        OrdersAdministrativeDivisionResponse secondOrdersAdministrativeDivisionResponse = restTemplate.getForObject("http://api.geonames.org/childrenJSON?geonameId=" + firstOrder.getGeonameId() + "&username="+userGeonames, OrdersAdministrativeDivisionResponse.class);
                        firstOrdersAdministrativeDivision.add(new OrdersAdministrativeDivision(firstOrder)); // add firstOrder
                        if (secondOrdersAdministrativeDivisionResponse == null || secondOrdersAdministrativeDivisionResponse.getGeonames() == null) {
                            System.err.println("No se encontraron ciudades para la región: " + firstOrder.getName());
                            return; // Continue with next firstOrder
                        }
                        List<OrdersAdministrativeDivision> secondOrdersAdministrativeDivision = new ArrayList<>();

                        secondOrdersAdministrativeDivisionResponse.getGeonames().forEach(secondOrder -> { // We iterate over all the first order to initialize all the DTOs
                            secondOrdersAdministrativeDivision.add(new OrdersAdministrativeDivision(secondOrder));
                        });
                        ordersAdministrativeDivisionRepository.saveAll(secondOrdersAdministrativeDivision); // save all "secondOrder"
                    }catch (Exception ex){
                        System.err.println(ex.getMessage());

                    }
                    });
                ordersAdministrativeDivisionRepository.saveAll(firstOrdersAdministrativeDivision); // save all "firstOrder"

                }catch (Exception e){
                    System.err.println(e.getMessage());
            }
            });
    }
}
