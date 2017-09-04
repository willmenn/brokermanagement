package com.brokermanagement.repository;


import com.brokermanagement.model.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ManagerRepository extends MongoRepository<Manager, String> {

    Manager findDistinctByManagerAndPassword(String manager, String password);

    List<Manager> findByManager(String manager);

    List<Manager> findAllByManager(String manager);
}
