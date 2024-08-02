package org.examen.webservice.service;

import org.examen.webservice.entity.Employee;
import org.examen.webservice.entity.EmployeeWorkedHours;
import org.examen.webservice.repository.EmployeeRepository;
import org.examen.webservice.repository.WorkHoursRepository;
import org.examen.webservice.request.WorkHoursRequest;
import org.examen.webservice.response.WorkHoursResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class WorkHoursService {

    @Autowired
    private WorkHoursRepository workHoursRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public WorkHoursResponse addWorkHours(WorkHoursRequest request) {

        Optional<Employee> employee = employeeRepository.findById(request.getEmployeeId());
        if (employee.isEmpty()) {
            return new WorkHoursResponse(null, false);
        }

        if (request.getWorkedHours() > 20) {
            return new WorkHoursResponse(null, false);
        }

        if (request.getWorkedDate().isAfter(LocalDate.now())) {
            return new WorkHoursResponse(null, false);
        }

        if (workHoursRepository.existsByEmployeeIdAndWorkedDate(request.getEmployeeId(), request.getWorkedDate())) {
            return new WorkHoursResponse(null, false);
        }

        EmployeeWorkedHours employeeWorkedHours = new EmployeeWorkedHours(employee.get(), request.getWorkedHours(), request.getWorkedDate());
        workHoursRepository.save(employeeWorkedHours);

        return new WorkHoursResponse(employeeWorkedHours.getId(), true);
    }
}
