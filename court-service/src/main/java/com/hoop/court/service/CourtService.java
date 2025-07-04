package com.hoop.court.service;

import com.hoop.court.dto.request.CourtRequestDTO;
import com.hoop.court.model.Court;

import java.util.List;

public interface CourtService {

    Court getCourt(String id);
    List<Court> getCourtsByCityId(long id);
    void updateCourt(Court court);
    void save(CourtRequestDTO courts);

}
