package org.examen.webservice.controller;

import org.examen.webservice.request.WorkHoursRequest;
import org.examen.webservice.response.WorkHoursResponse;
import org.examen.webservice.service.WorkHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workhours")
public class WorkHoursController {

    @Autowired
    private WorkHoursService workHoursService;

    @PostMapping("/add")
    public ResponseEntity<?> addWorkHours(@RequestBody WorkHoursRequest request) {
        try {
            WorkHoursResponse response = workHoursService.addWorkHours(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
