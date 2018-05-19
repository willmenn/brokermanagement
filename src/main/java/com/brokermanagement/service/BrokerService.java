package com.brokermanagement.service;

import com.brokermanagement.client.ScheduleClient;
import com.brokermanagement.exception.BrokerNotFound;
import com.brokermanagement.model.Broker;
import com.brokermanagement.repository.BrokerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class BrokerService {

    private static final String BROKER_NOT_FOUND = "Broker Not Found";
    private BrokerRepository brokerRepository;
    private ScheduleClient scheduleClient;

    @Autowired
    public BrokerService(BrokerRepository brokerRepository, ScheduleClient scheduleClient) {
        this.brokerRepository = brokerRepository;
        this.scheduleClient = scheduleClient;
    }

    public Broker getBroker(String id) {
        return brokerRepository.findOne(id);
    }

    public Broker getBrokerByPassword(String name, String password) {
        return brokerRepository.findByNameAndPassword(name, password);
    }

    public List<Broker> getBrokersByManager(String managerName) {
        return brokerRepository.findByManager(managerName);
    }

    public Broker createBroker(Broker broker) {
        return brokerRepository.save(broker);
    }

    public void delete(String id) {
        brokerRepository.delete(id);
    }

    public Broker updateBroker(String id, Broker broker) {
        if (brokerRepository.exists(id)) {
            return brokerRepository.save(Broker.builder()
                    .brokerId(id)
                    .name(broker.getName())
                    .manager(broker.getManager())
                    .preference(broker.getPreference())
                    .build());
        } else {
            throw new BrokerNotFound(BROKER_NOT_FOUND, id);
        }
    }

    public int getCountOfBrokersByManager(String managerName) {
        return brokerRepository.countByManager(managerName).intValue();
    }
}
