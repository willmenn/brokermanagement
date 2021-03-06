package com.brokermanagement.repository;

import com.brokermanagement.model.Broker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrokerRepository extends MongoRepository<Broker, String> {

    List<Broker> findByManager(String manager);

    Broker findByNameAndPassword(String name,String password);

    List<Broker> findByName(String name);

    Long countByManager(String name);
}
