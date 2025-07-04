package com.hoop.court.service;

import com.hoop.court.dto.request.CourtRequestDTO;
import com.hoop.court.exception.RequestException;
import com.hoop.court.model.Court;
import com.hoop.court.repository.CourtRepository;
import com.hoop.court.repository.OrdersAdministrativeDivisionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CourtServiceImp implements  CourtService{

    private CourtRepository courtRepository;
    private OrdersAdministrativeDivisionRepository ordersAdministrativeDivisionRepository;

    @Override
    public Court getCourt(String id) {
        return courtRepository.findById(id).orElseThrow(() -> new RequestException("There is no court with id "+id,"400-Bad Request"));
    }

    @Override
    public List<Court> getCourtsByCityId(long id) {
        List<Court> courts = courtRepository.findByCityGeonameId(id);
        if(courts.isEmpty()){
            throw new RequestException("There is no court with city id "+id,"400-Bad Request");
        }
        return courts;
    }

    @Override
    public void updateCourt(Court courtUpdate) {
        Court court = courtRepository.findById(courtUpdate.getCourtGmaps())
                .orElseThrow(() -> new RequestException("There is no court with id "+courtUpdate.getCourtGmaps(),"400-Bad Request"));
        /*Añadir solo informacion que se desea actualizar*/
        court.setName(courtUpdate.getName());
        court.setCityCourt(courtUpdate.getCityCourt());
        court.setCourtImages(courtUpdate.getCourtImages());
        court.setTypeCourt(courtUpdate.getTypeCourt());
        court.setAlternativeName(courtUpdate.getAlternativeName());
        courtRepository.save(court);
    }

    @Override
    public void save(CourtRequestDTO courtsDTO) {
        List<Court> courts = new ArrayList<>();
        courtsDTO.getPlaces().forEach(courtDTO -> {
            if(courtRepository.findById(courtDTO.id()).isEmpty())
            {
                long geonamesId = ordersAdministrativeDivisionRepository
                        .findByAdminName1AndName(courtDTO.postalAddress().administrativeArea(), courtDTO.postalAddress().locality())
                        .orElseThrow(() -> new RequestException("There is no city with name "+courtDTO.postalAddress().locality()+" and first order administrative with name "+ courtDTO.postalAddress().administrativeArea(),"400-Bad Request")).getGeonameId();

                courts.add(new Court(courtDTO, geonamesId));
            }else {
                throw new RequestException("There is already a basketball court with ID "+courtDTO.id(), "400-Bad Request");
            }
        });
        courtRepository.saveAll(courts);
    }
}
