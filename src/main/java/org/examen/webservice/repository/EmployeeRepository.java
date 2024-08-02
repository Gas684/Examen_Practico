package org.examen.webservice.repository;

import org.examen.webservice.entity.Employee;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface EmployeeRepository extends ListCrudRepository<Employee, Integer> {

    boolean existsByNameAndLastName(String name, String lastName) ;

    List<Employee> findByJobId(Integer jobId);

}
