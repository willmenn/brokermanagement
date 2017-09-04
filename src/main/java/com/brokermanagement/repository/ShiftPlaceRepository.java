package com.brokermanagement.repository;


import com.brokermanagement.model.ShiftPlace;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShiftPlaceRepository extends MongoRepository<ShiftPlace, String> {
    List<ShiftPlace> findByManagersName(String managerName);

    Long countByManagersName(String managerName);
}
