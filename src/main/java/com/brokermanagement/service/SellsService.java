package com.brokermanagement.service;

import com.brokermanagement.exception.SellsNotFound;
import com.brokermanagement.model.Sells;
import com.brokermanagement.repository.SellsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellsService {

    private static final String SELLS_NOT_FOUND = "Sells Not Found";
    private SellsRepository repository;

    @Autowired
    public SellsService(SellsRepository repository) {
        this.repository = repository;
    }

    public Sells getSellsId(String id) {
        return repository.findOne(id);
    }

    public List<Sells> getSellsByManager(String name) {
        return repository.findByManager(name);
    }

    public Sells createSells(Sells sells) {
        return repository.save(sells);
    }

    public void delete(String id) {
        repository.delete(id);
    }

    public Sells updateSells(String id, Sells sells) {
        if (repository.exists(id)) {
            return repository.save(Sells.builder()
                    .id(sells.getId()).build());
        } else {
            throw new SellsNotFound(SELLS_NOT_FOUND, id);
        }
    }
}
