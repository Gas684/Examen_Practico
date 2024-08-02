package org.examen.webservice.repository;

import org.examen.webservice.entity.EmployeeWorkedHours;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface WorkHoursRepository extends ListCrudRepository<EmployeeWorkedHours, Integer> {

    boolean existsByEmployeeIdAndWorkedDate(int employeeId, LocalDate workedDate);

    @Query("SELECT SUM(wh.workedHours) FROM EmployeeWorkedHours wh WHERE wh.employee.id = :employeeId AND wh.workedDate BETWEEN :startDate AND :endDate")
    Integer findTotalWorkedHoursByEmployeeIdAndDateRange(@Param("employeeId") Integer employeeId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
