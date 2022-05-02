package com.laith.employee.controller;


import com.laith.employee.enums.EmploymentType;
import com.laith.employee.model.Employee;
import com.laith.employee.service.EmployeeService;
import com.laith.employee.service.EmployeeTypeValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeTypeValidationService employeeTypeValidationService;

    @GetMapping("/all")//localhost:8080/employee/all
    public ResponseEntity<List<Employee>> getAllEmployees() {
        log.info("Inside the endpoint getAllEmployees");
        List<Employee> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/find/{type}")//localhost:8080/employee/find/Contract
    public ResponseEntity<Employee> getEmployeesByTheirType(@PathVariable("type") String type) {
        log.info("Inside the endpoint getEmployeesByTheirType");
        employeeTypeValidationService.validateEmployeeType(type);
        EmploymentType employmentType = EmploymentType.forTypeIgnoreCase(type);
        List<Employee> employees = employeeService.findEmployeesByTheirType(employmentType);
        return new ResponseEntity(employees, HttpStatus.OK);
    }

    @GetMapping("/full-names")//localhost:8080/employee/full-names
    public ResponseEntity<String> getEmployeeFullNames() {
        log.info("Inside the endpoint getEmployeeFullNames");
        String employeesFullNames = employeeService.findEmployeesFullNames();
        return ResponseEntity.ok(employeesFullNames);
    }

    @GetMapping("/get-emails")//localhost:8080/employee/get-emails
    public ResponseEntity<String> getValidAndInvalidEmails() {
        log.info("Inside the endpoint getValidAndInvalidEmails");
        String validAndInvalidEmails = employeeService.getValidAndInvalidEmails();
        return ResponseEntity.ok(validAndInvalidEmails);
    }

    @GetMapping("/names-emails")//localhost:8080/employee/names-emails
    public ResponseEntity<String> getCamelCaseEmail() {
        log.info("Inside the endpoint getCamelCaseEmail");
        String namesWithEmails = employeeService.getNamesCamelCasingWithEmails();
        return ResponseEntity.ok(namesWithEmails);
    }
}
