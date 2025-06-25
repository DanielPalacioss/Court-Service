package com.hoop.court.client.geonames;

import com.hoop.court.client.geonames.response.CountryResponse;
import com.hoop.court.client.geonames.response.OrdersAdministrativeDivisionResponse;
import com.hoop.court.dto.OrdersAdministrativeDivisionDTO;
import com.hoop.court.error.RequestException;
import com.hoop.court.model.Country;
import com.hoop.court.repository.CountryRepository;
import com.hoop.court.repository.OrdersAdministrativeDivisionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GeoNamesClient {

    private final RestTemplate restTemplate = new RestTemplate() ;
    private final CountryRepository countryRepository;
    private final OrdersAdministrativeDivisionRepository ordersAdministrativeDivisionRepository;

    public void addCountries() {
        CountryResponse countryResponse = restTemplate.
                getForObject("http://api.geonames.org/countryInfoJSON?username=mrdaniel", CountryResponse.class);
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
                        getForObject("http://api.geonames.org/childrenJSON?geonameId=" + country.getGeonameId() + "&username=mrdaniel", OrdersAdministrativeDivisionResponse.class);
                if (firstOrdersAdministrativeDivisionResponse == null || firstOrdersAdministrativeDivisionResponse.getGeonames() == null) {
                    System.err.println("No data found for the country: " + country.getCountryName());
                    return; // Continue with next country
                }
                List<OrdersAdministrativeDivisionDTO> firstOrdersAdministrativeDivisionDTO = new ArrayList<>();

                firstOrdersAdministrativeDivisionResponse.getGeonames().forEach(firstOrder -> { // Iterate over all firstOrders
                    try {
                        if(ordersAdministrativeDivisionRepository.findById(firstOrder.getGeonameId()).isPresent()) return; // If the first order administrative is found, there are already second order administrative. Continue with next firstOrder
                        OrdersAdministrativeDivisionResponse secondOrdersAdministrativeDivisionResponse = restTemplate.getForObject("http://api.geonames.org/childrenJSON?geonameId=" + firstOrder.getGeonameId() + "&username=mrdaniel", OrdersAdministrativeDivisionResponse.class);
                        firstOrdersAdministrativeDivisionDTO.add(new OrdersAdministrativeDivisionDTO(firstOrder)); // add firstOrder
                        if (secondOrdersAdministrativeDivisionResponse == null || secondOrdersAdministrativeDivisionResponse.getGeonames() == null) {
                            System.err.println("No se encontraron ciudades para la región: " + firstOrder.getName());
                            return; // Continue with next firstOrder
                        }
                        List<OrdersAdministrativeDivisionDTO> secondOrdersAdministrativeDivisionDTO = new ArrayList<>();

                        secondOrdersAdministrativeDivisionResponse.getGeonames().forEach(secondOrder -> { // We iterate over all the first order to initialize all the DTOs
                            secondOrdersAdministrativeDivisionDTO.add(new OrdersAdministrativeDivisionDTO(secondOrder));
                        });
                        ordersAdministrativeDivisionRepository.saveAll(secondOrdersAdministrativeDivisionDTO); // save all "secondOrder"
                    }catch (Exception ex){
                        System.err.println(ex.getMessage());

                    }
                    });
                ordersAdministrativeDivisionRepository.saveAll(firstOrdersAdministrativeDivisionDTO); // save all "firstOrder"

                }catch (Exception e){
                    System.err.println(e.getMessage());
            }
            });
    }
}
