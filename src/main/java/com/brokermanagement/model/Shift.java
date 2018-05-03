package com.brokermanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shift {
    private Integer morning;
    private Integer afternoon;
    private Integer night;

    @JsonIgnore
    public boolean isNotNull(){
        return this.morning != null
                || this.afternoon != null
                || this.night != null;
    }
}
