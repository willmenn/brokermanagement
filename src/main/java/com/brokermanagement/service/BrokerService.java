package com.brokermanagement.service;

import com.brokermanagement.model.Broker;
import com.brokermanagement.repository.BrokerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrokerService {

    private BrokerRepository repository;

    public Broker getBroker(Integer id) {
        return repository.findOne(id);
    }

}
