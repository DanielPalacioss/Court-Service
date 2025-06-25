package com.hoop.court.repository;

import com.hoop.court.dto.OrdersAdministrativeDivisionDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersAdministrativeDivisionRepository extends MongoRepository<OrdersAdministrativeDivisionDTO,Long> {

    Optional<List<OrdersAdministrativeDivisionDTO>> findByCountryIdAndFcodeName(String countryId, String fcodeName);

    Optional<List<OrdersAdministrativeDivisionDTO>> findByAdminName1AndFcodeNameAndCountryId(String adminName1, String fcodeName, String countryId);

    Optional<OrdersAdministrativeDivisionDTO> findByAdminName1AndName(String adminName1, String toponymName);
}
