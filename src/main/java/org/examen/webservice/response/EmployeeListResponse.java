package org.examen.webservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.examen.webservice.entity.Employee;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeListResponse {

    private List<Employee> employees;
    private boolean success;

}
