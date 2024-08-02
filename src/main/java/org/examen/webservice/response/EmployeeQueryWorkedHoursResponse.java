package org.examen.webservice.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeQueryWorkedHoursResponse {

    @JsonProperty("total_worked_hours")
    private Integer totalWorkedHours;
    private boolean success;

}
