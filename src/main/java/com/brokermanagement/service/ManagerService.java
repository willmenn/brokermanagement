package com.brokermanagement.service;

import com.brokermanagement.exception.ManagerNotFoundException;
import com.brokermanagement.model.Manager;
import com.brokermanagement.model.Manager.Message;
import com.brokermanagement.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;
import static java.util.Comparator.comparing;

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

    public List<Message> getMessagesByManger(String manager) {
        List<Message> messages = get(manager).getMessages();
        if (messages == null) {
            return newArrayList();
        } else {
            List<Message> response = sortMessages(messages);
            return response;
        }
    }

    private List<Message> sortMessages(List<Message> messages) {
        List<Message> response = newArrayList();
        List<Message> collect = messages.stream()
                .sorted(comparing(Message::getCreatedTimestamp).reversed())
                .collect(Collectors.toList());
        int length = collect.size() > 10 ? 10 : collect.size();
        for (int i = 0; i < length; i++) {
            response.add(collect.get(i));
        }
        return response;
    }

    public List<Message> createMessage(String managerName, String message) {
        Manager manager = get(managerName);
        List<Message> messages = manager.getMessages();
        if (messages == null) {
            messages = newArrayList();
        }
        messages.add(Message.builder().message(message)
                .createdTimestamp(LocalDateTime.now(Clock.systemDefaultZone()))
                .build());
        return sortMessages(save(Manager.builder()
                .id(manager.getId())
                .password(manager.getPassword())
                .scheduleId(manager.getScheduleId())
                .messages(messages)
                .manager(manager.getManager())
                .build()).getMessages());

    }
}
