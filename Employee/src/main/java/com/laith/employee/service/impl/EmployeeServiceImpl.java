package com.laith.employee.service.impl;

import com.laith.employee.enums.EmploymentType;
import com.laith.employee.model.Employee;
import com.laith.employee.repository.EmployeeRepository;
import com.laith.employee.service.EmailValidatorService;
import com.laith.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmailValidatorService emailValidatorService;

    public List<Employee> findAllEmployees() {
        log.info("Inside the findAllEmployees method in EmployeeServiceImpl");
        return employeeRepository.findAll();
    }

    public List<Employee> findEmployeesByTheirType(EmploymentType employmentType) {
        log.info("Inside the findEmployeesByTheirType method in EmployeeServiceImpl");
        return employeeRepository.findAllByEmploymentType(employmentType);
    }

    public String findEmployeesFullNames() {
        log.info("Inside the findEmployeesFullNames method in EmployeeServiceImpl");
        return getFullNames();
    }

    public String getValidAndInvalidEmails() {
        log.info("Inside the getValidAndInvalidEmails method in EmployeeServiceImpl");
        StringBuilder validEmails = new StringBuilder();
        StringBuilder invalidEmails = new StringBuilder();
        appendTitles(validEmails, invalidEmails);
        return getValidWithInValidEmails(validEmails, invalidEmails);
    }

    public String getNamesCamelCasingWithEmails() {
        log.info("Inside the getNamesCamelCasingWithEmails method in EmployeeServiceImpl");
        StringBuilder name = new StringBuilder();
        for (Employee e : employeeRepository.findAll()) {
            if (isNotEmptyOrNull(e.getFirstName()))
                name.append(lower(e.getFirstName()));
            if (isNotEmptyOrNull(e.getMiddleName())) {
                name.append(capitalize(e.getMiddleName()));
            }
            if (isNotEmptyOrNull(e.getLastName())) {
                name.append(capitalize(e.getLastName()));
            }
            name.append(" ");
            name.append(e.getEmail().toUpperCase());
            name.append("\n");
        }
        return name.toString();

    }

    private String getFullNames() {
        StringBuilder name = new StringBuilder();
        for (Employee e : employeeRepository.findAll()) {
            if (isNotEmptyOrNull(e.getFirstName()))
                name.append(e.getFirstName() + " ");
            if (isNotEmptyOrNull(e.getMiddleName()))
                name.append(e.getMiddleName() + " ");
            if (isNotEmptyOrNull(e.getLastName()))
                name.append(e.getLastName() + " ");
            name.append("\n");
        }
        return name.toString();
    }

    private void appendTitles(StringBuilder validEmails, StringBuilder invalidEmails) {
        validEmails.append("Valid Emails");
        validEmails.append("\n");
        invalidEmails.append("Invalid Emails");
        invalidEmails.append("\n");
    }

    private String getValidWithInValidEmails(StringBuilder validEmails, StringBuilder invalidEmails) {
        for (Employee e : employeeRepository.findAll()) {
            if (emailValidatorService.isValidEmail(e.getEmail())) {
                validEmails.append(e.getEmail());
                validEmails.append("\n");
            } else {
                invalidEmails.append(e.getEmail());
                invalidEmails.append("\n");
            }
        }
        return validEmails + "\n" + invalidEmails;
    }

    public static String capitalize(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static String lower(String name) {
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }

    private boolean isNotEmptyOrNull(String name) {
        return name != null && name.isEmpty() == false;
    }

}
