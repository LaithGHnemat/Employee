package com.laith.employee.repository;

import com.laith.employee.enums.EmploymentType;
import com.laith.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByEmploymentType(EmploymentType employmentType);

}