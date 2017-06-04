package com.brokermanagement.service;

import com.brokermanagement.exception.ShiftPlaceNotFound;
import com.brokermanagement.model.ShiftPlace;
import com.brokermanagement.repository.ShiftPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<ShiftPlace> getShiftPlaceByManagersName(String name) {
        return repository.findByManagersName(name);
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
                    .shiftPlaceId(id)
                    .name(shiftPlace.getName())
                    .address(shiftPlace.getAddress())
                    .managersName(shiftPlace.getManagersName())
                    .days(shiftPlace.getDays())
                    .places(shiftPlace.getPlaces())
                    .build());
        } else {
            throw new ShiftPlaceNotFound("Broker Not Found", id);
        }
    }
}
