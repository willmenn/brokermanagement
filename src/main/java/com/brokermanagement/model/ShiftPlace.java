package com.brokermanagement.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
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

    @NotNull
    Map<DayEnum, Shift> daysV3;


    public String getPlaces() {
        int sum = this.getDaysV3().values().stream()
                .mapToInt(sp -> sp.getAfternoon() + sp.getMorning() + sp.getNight())
                .sum();

        return String.valueOf(sum);
    }
}
