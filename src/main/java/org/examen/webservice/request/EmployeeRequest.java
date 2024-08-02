package org.examen.webservice.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeRequest {

    @JsonProperty("gender_id")
    private int genderId;
    @JsonProperty("job_id")
    private int jobId;
    private String name;
    @JsonProperty("last_name")
    private String lastName;
    private LocalDate birthdate;

}

