package org.examen.webservice.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkHoursRequest {

    @JsonProperty("employee_id")
    private int employeeId;
    @JsonProperty("worked_hours")
    private int workedHours;
    @JsonProperty("worked_date")
    private LocalDate workedDate;

}
