package com.brokermanagement.repository;


import com.brokermanagement.model.Sells;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellsRepository extends MongoRepository<Sells, String> {

    List<Sells> findByManager(String manager);
}
