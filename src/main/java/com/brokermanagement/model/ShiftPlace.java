package com.brokermanagement.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Document(collection = "shiftplace")
public class ShiftPlace {
    @Id
    private String shiftPlaceId;
    private String name;
    private String address;
    private String places;
    private String managersName;
    private Map<String, Integer> days;
}
