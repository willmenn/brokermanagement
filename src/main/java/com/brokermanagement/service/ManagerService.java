package com.brokermanagement.service;

import com.brokermanagement.model.Manager;
import com.brokermanagement.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

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
        return repository.findDistinctByManagerAndPassword(manager,pass);
    }

    public  Manager updateManagerSchedule(String manager,String scheduleId){
        Manager managerModel = repository.findAllByManager(manager).stream().findFirst().get();
        managerModel.setScheduleId(scheduleId);

        brokerService.updateBrokers(manager,scheduleId);

        return save(managerModel);
    }
}
