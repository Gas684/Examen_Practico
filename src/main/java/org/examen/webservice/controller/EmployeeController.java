package org.examen.webservice.controller;

import org.examen.webservice.request.EmployeeQueryRequest;
import org.examen.webservice.request.EmployeeRequest;
import org.examen.webservice.request.JobRequest;
import org.examen.webservice.response.EmployeeListResponse;
import org.examen.webservice.response.EmployeeQueryPaymentsResponse;
import org.examen.webservice.response.EmployeeQueryWorkedHoursResponse;
import org.examen.webservice.response.EmployeeResponse;
import org.examen.webservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/employees",  produces = "application/json")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        try {
            EmployeeResponse response = employeeService.addEmployee(employeeRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/job")
    public ResponseEntity<?> getEmployeesByJobId(@RequestBody JobRequest jobRequest) {
        try {
            EmployeeListResponse response =  employeeService.getEmployeesByJobId(jobRequest.getJobId());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/totalWorkedHours")
    public ResponseEntity<?> getTotalWorkedHours(@RequestBody EmployeeQueryRequest employeeQueryRequest) {
        try {
            EmployeeQueryWorkedHoursResponse response = employeeService.getTotalWorkedHours(employeeQueryRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/payments")
    public ResponseEntity<?> getPayments(@RequestBody EmployeeQueryRequest employeeQueryRequest) {
        try {
            EmployeeQueryPaymentsResponse response = employeeService.getPayments(employeeQueryRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
