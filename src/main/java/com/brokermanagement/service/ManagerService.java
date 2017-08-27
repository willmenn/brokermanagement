package com.brokermanagement.service;

import com.brokermanagement.model.Manager;
import com.brokermanagement.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

    private ManagerRepository repository;

    @Autowired
    private ManagerService(ManagerRepository repository) {
        this.repository = repository;
    }

    public Manager save(Manager manager) {
        return repository.save(manager);
    }
}
