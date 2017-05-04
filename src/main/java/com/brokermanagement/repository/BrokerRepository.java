package com.brokermanagement.repository;

import com.brokermanagement.model.Broker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrokerRepository extends CrudRepository<Broker, Integer> {
}
