package com.brokermanagement.repository;


import com.brokermanagement.model.ShiftPlace;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShiftPlaceRepository extends MongoRepository<ShiftPlace, String> {
}
