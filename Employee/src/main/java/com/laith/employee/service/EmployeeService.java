package com.laith.employee.service;

import com.laith.employee.enums.EmploymentType;
import com.laith.employee.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAllEmployees();

    List<Employee> findEmployeesByTheirType(EmploymentType employmentType);

    String findEmployeesFullNames();

    String getValidAndInvalidEmails();

    String getNamesCamelCasingWithEmails();
}
