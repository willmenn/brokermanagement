package com.brokermanagement.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Component
public class ScheduleClient {

    private RestTemplate restTemplate;

    @Autowired
    private ScheduleClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, List<String>> fetchScheduleForAllBrokers(String scheduleId, String manager) {
        UriComponents url = UriComponentsBuilder
                .fromHttpUrl("http://broker-scheduler.herokuapp.com/schedule/broker")
                .queryParam("id", scheduleId)
                .queryParam("manager", manager).build();
        return restTemplate.getForEntity(url.toUri(), Map.class).getBody();
    }
}
