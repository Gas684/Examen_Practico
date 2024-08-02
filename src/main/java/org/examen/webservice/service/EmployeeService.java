package org.examen.webservice.service;

import org.examen.webservice.entity.Employee;
import org.examen.webservice.entity.Gender;
import org.examen.webservice.entity.Job;
import org.examen.webservice.repository.EmployeeRepository;
import org.examen.webservice.repository.GenderRepository;
import org.examen.webservice.repository.JobRepository;
import org.examen.webservice.repository.WorkHoursRepository;
import org.examen.webservice.request.EmployeeQueryRequest;
import org.examen.webservice.request.EmployeeRequest;
import org.examen.webservice.response.EmployeeListResponse;
import org.examen.webservice.response.EmployeeQueryPaymentsResponse;
import org.examen.webservice.response.EmployeeQueryWorkedHoursResponse;
import org.examen.webservice.response.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private WorkHoursRepository workHoursRepository;

    public EmployeeResponse addEmployee(EmployeeRequest request) {
        if (employeeRepository.existsByNameAndLastName(request.getName(), request.getLastName())) {
            return new EmployeeResponse(null, false);
        }

        if (Period.between(request.getBirthdate(), LocalDate.now()).getYears() < 18) {
            return new EmployeeResponse(null, false);
        }

        Optional<Gender> gender = genderRepository.findById(request.getGenderId());
        if (gender.isEmpty()) {
            return new EmployeeResponse(null, false);
        }

        Optional<Job> job = jobRepository.findById(request.getJobId());
        if (job.isEmpty()) {
            return new EmployeeResponse(null, false);
        }

        Employee employee = new Employee(gender.get(), job.get(), request.getName(), request.getLastName(), request.getBirthdate());
        employeeRepository.save(employee);

        return new EmployeeResponse(employee.getId(), true);
    }

    public EmployeeListResponse getEmployeesByJobId(Integer jobId) {
        Optional<Job> job = jobRepository.findById(jobId);
        if (job.isEmpty()) {
            return new EmployeeListResponse(null, false);
        }
        List<Employee> employees = employeeRepository.findByJobId(jobId);
        if (employees.isEmpty()) {
            return new EmployeeListResponse(null, false);
        }
        return new EmployeeListResponse(employees, true);
    }

    public EmployeeQueryWorkedHoursResponse getTotalWorkedHours(EmployeeQueryRequest employeeQueryRequest) {

        Optional<Employee> employee = validateIfEmployeeExists(employeeQueryRequest.getEmployeeId());
        if (employee.isEmpty()) {
            return new EmployeeQueryWorkedHoursResponse(null, false);
        }
        if (validateIfStartDateIsAfter(employeeQueryRequest.getStartDate(), employeeQueryRequest.getEndDate())) {
            return new EmployeeQueryWorkedHoursResponse(null, false);
        }

        Integer workHours = workHoursRepository.findTotalWorkedHoursByEmployeeIdAndDateRange(employeeQueryRequest.getEmployeeId(),
                employeeQueryRequest.getStartDate(), employeeQueryRequest.getEndDate());

        return new EmployeeQueryWorkedHoursResponse(workHours, true);
    }

    public EmployeeQueryPaymentsResponse getPayments(EmployeeQueryRequest employeeQueryRequest) {

        Optional<Employee> employee = validateIfEmployeeExists(employeeQueryRequest.getEmployeeId());
        if (employee.isEmpty()) {
            return new EmployeeQueryPaymentsResponse(null, false);
        }
        if (validateIfStartDateIsAfter(employeeQueryRequest.getStartDate(), employeeQueryRequest.getEndDate())) {
            return new EmployeeQueryPaymentsResponse(null, false);
        }

        Integer workHours = workHoursRepository.findTotalWorkedHoursByEmployeeIdAndDateRange(employeeQueryRequest.getEmployeeId(),
                employeeQueryRequest.getStartDate(), employeeQueryRequest.getEndDate());

        Job job = jobRepository.findById(employee.get().getJob().getId()).get();

        Double payments = workHours * job.getSalary();

        return new EmployeeQueryPaymentsResponse(payments, true);
    }

    public Optional<Employee> validateIfEmployeeExists(Integer employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public boolean validateIfStartDateIsAfter(LocalDate startDate, LocalDate endDate) {
        return startDate.isAfter(endDate);
    }
}
