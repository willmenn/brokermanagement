package com.brokermanagement.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Document(collection = "manager")
public class Manager {
    @Id
    private String id;
    private String manager;
    private String password;
    @Setter
    private String scheduleId;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    public static class Message {
        private String message;
        private LocalDateTime createdTimestamp;

    }

    private List<Message> messages;
}
