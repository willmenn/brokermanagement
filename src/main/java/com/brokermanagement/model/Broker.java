package com.brokermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

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
    private String password;
    @Setter
    private List<String> daysScheduled;
    private Map<Constraint, List<String>> constraints;

    public String getQtdConstraints() {
        int sum = this.constraints.values().stream().mapToInt(value -> value.size()).sum();
        return String.valueOf(sum);
    }
}
