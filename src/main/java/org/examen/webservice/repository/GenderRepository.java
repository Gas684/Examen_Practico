package org.examen.webservice.repository;

import org.examen.webservice.entity.Employee;
import org.examen.webservice.entity.Gender;
import org.springframework.data.repository.ListCrudRepository;

public interface GenderRepository extends ListCrudRepository<Gender, Integer> {

}
