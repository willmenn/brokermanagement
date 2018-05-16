package com.brokermanagement.service;

import com.brokermanagement.exception.ManagerNotFoundException;
import com.brokermanagement.model.Manager;
import com.brokermanagement.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
public class ManagerService {

    private static final String MANAGER_NOT_FOUND = "Could not find Manager, for name %s";
    private ManagerRepository repository;
    private BrokerService brokerService;

    @Autowired
    private ManagerService(ManagerRepository repository, BrokerService brokerService) {
        this.repository = repository;
        this.brokerService = brokerService;
    }

    public Manager save(Manager manager) {
        return repository.save(manager);
    }

    public Manager get(String manager, String pass) {
        Manager managerModel = repository.findDistinctByManagerAndPassword(manager, pass);
        if (managerModel == null) {
            throw new ManagerNotFoundException(format(MANAGER_NOT_FOUND, manager));
        } else {
            return managerModel;
        }
    }

    public Manager get(String manager) {
        List<Manager> managerModel = repository.findByManager(manager);
        if (managerModel == null) {
            throw new ManagerNotFoundException(format(MANAGER_NOT_FOUND, manager));
        } else {
            return managerModel.stream().findFirst()
                    .orElseThrow(() -> new RuntimeException("Could Not find Manager"));
        }
    }

    public Manager updateManagerSchedule(String manager, String scheduleId) {
        Manager managerModel = repository.findByManager(manager).stream().findFirst().get();
        managerModel.setScheduleId(scheduleId);

//        brokerService.updateBrokers(manager,scheduleId);

        return save(managerModel);
    }
}
