package org.examen.webservice.repository;

import org.examen.webservice.entity.Employee;
import org.examen.webservice.entity.Job;
import org.springframework.data.repository.ListCrudRepository;

public interface JobRepository extends ListCrudRepository<Job, Integer> {

}
