package com.hoop.court.repository;

import com.hoop.court.model.OrdersAdministrativeDivision;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersAdministrativeDivisionRepository extends MongoRepository<OrdersAdministrativeDivision,Long> {

    List<OrdersAdministrativeDivision> findByCountryIdAndFcodeName(String countryId, String fcodeName);

    List<OrdersAdministrativeDivision> findByAdminName1AndFcodeNameAndCountryId(String adminName1, String fcodeName, String countryId);

    Optional<OrdersAdministrativeDivision> findByAdminName1AndName(String adminName1, String toponymName);
}
