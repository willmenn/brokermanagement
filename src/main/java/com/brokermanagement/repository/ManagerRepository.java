package com.brokermanagement.repository;


import com.brokermanagement.model.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ManagerRepository extends MongoRepository<Manager, String> {

    Manager findDistinctByManagerAndPassword(String manager, String password);
}
