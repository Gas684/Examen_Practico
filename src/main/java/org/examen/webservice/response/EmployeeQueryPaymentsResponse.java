package org.examen.webservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeQueryPaymentsResponse {

    private Double payments;
    private boolean success;

}
