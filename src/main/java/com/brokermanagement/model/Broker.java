package com.brokermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Document(collection = "broker")
public class Broker {
    @Id
    private String brokerId;
    private String name;
    private String manager;
    private Preferences preference;
}
