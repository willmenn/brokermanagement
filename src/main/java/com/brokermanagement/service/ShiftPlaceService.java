package com.brokermanagement.service;

import com.brokermanagement.exception.ShiftPlaceNotFound;
import com.brokermanagement.model.ShiftPlace;
import com.brokermanagement.repository.ShiftPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiftPlaceService {

    private ShiftPlaceRepository repository;

    @Autowired
    public ShiftPlaceService(ShiftPlaceRepository repository) {
        this.repository = repository;
    }

    public ShiftPlace getShiftPlace(String id) {
        return repository.findOne(id);
    }

    public ShiftPlace createShiftPlace(ShiftPlace shiftPlace) {
        return repository.save(shiftPlace);
    }

    public void deleteShiftPlace(String id) {
        repository.delete(id);
    }

    public ShiftPlace updateShiftPlace(String id, ShiftPlace shiftPlace) {
        if (repository.exists(id)) {
            return repository.save(ShiftPlace.builder()
                    .name(shiftPlace.getName())
                    .address(shiftPlace.getAddress())
                    .managersName(shiftPlace.getManagersName())
                    .build());
        } else {
            throw new ShiftPlaceNotFound("Broker Not Found", id);
        }
    }
}
