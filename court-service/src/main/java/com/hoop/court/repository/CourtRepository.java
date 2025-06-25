package com.hoop.court.repository;

import com.hoop.court.model.Court;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourtRepository extends MongoRepository<Court,String> {

    Optional<List<Court>> findByCityGeonameId(long id);
}
