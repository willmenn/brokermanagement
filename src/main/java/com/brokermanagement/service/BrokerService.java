package com.brokermanagement.service;

import com.brokermanagement.model.Broker;
import com.brokermanagement.repository.BrokerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrokerService {

    private BrokerRepository brokerRepository;

    @Autowired
    public BrokerService(BrokerRepository brokerRepository) {
        this.brokerRepository = brokerRepository;
    }

    public Broker getBroker(String id) {
        return brokerRepository.findOne(id);
    }

    public Broker createBroker(Broker broker) {
        return brokerRepository.save(broker);
    }
}
