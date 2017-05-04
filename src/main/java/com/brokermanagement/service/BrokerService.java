package com.brokermanagement.service;

import com.brokermanagement.model.Broker;
import com.brokermanagement.repository.BrokerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrokerService {

    private BrokerRepository repository;

    @Autowired
    public BrokerService(BrokerRepository repository) {
        this.repository = repository;
    }

    public Broker getBroker(Integer id) {
        return repository.findOne(id);
    }

    public Broker createBroker(Broker broker) {
        return repository.save(broker);
    }
}
