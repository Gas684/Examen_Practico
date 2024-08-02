package org.examen.webservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "employee_worked_hours")
public class EmployeeWorkedHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "worked_hours", nullable = false)
    private int workedHours;

    @Column(name = "worked_date", nullable = false)
    private LocalDate workedDate;

    public EmployeeWorkedHours(Employee employee, Integer workedHours, LocalDate workedDate) {
        this.employee = employee;
        this.workedHours = workedHours;
        this.workedDate = workedDate;
    }

}
