package com.hoop.court.client.geonames.response;

import com.hoop.court.model.Country;
import lombok.Data;

import java.util.List;

@Data
public class CountryResponse {
    private List<Country> geonames;
}
