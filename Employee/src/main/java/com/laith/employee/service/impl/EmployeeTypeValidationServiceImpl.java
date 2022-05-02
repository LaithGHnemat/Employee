package com.laith.employee.service.impl;

import com.laith.employee.exceptions.NotFoundEmployeeTypeException;
import com.laith.employee.service.EmployeeTypeValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeTypeValidationServiceImpl implements EmployeeTypeValidationService {
    public void validateEmployeeType(String employeeType){
        log.info("Inside the validateEmployeeType method in EmployeeTypeValidationServiceImpl");
        if(!(employeeType.equalsIgnoreCase("PERMANENT") ||
                employeeType.equalsIgnoreCase("INTERN") ||
                employeeType.equalsIgnoreCase("CONTRACT")
        )){
            throw  new NotFoundEmployeeTypeException("There is no such employee type");
        }
    }
}
