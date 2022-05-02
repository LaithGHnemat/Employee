package com.laith.employee.config;


import com.laith.employee.enums.EmploymentType;
import com.laith.employee.model.Employee;
import com.laith.employee.reader.JsonReader;
import com.laith.employee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.List;
   /*
   Here two ways to insert the given data into H2 DB
   both of them are true
   */

@Configuration
@Slf4j
public class EmployeeConfig {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JsonReader jsonReader;

    @Bean
    CommandLineRunner commandLineRunner() {
        log.info("Inside the CommandLineRunner to insert the data into H2 DB ");
        return args -> {
            //  insertData();
            insertDataFromJson();
        };

    }

    private void insertDataFromJson() throws IOException {
        List<Employee> employees = jsonReader.getEmployees();
        for (Employee e : employees) {
            employeeRepository.save(e);
        }
        log.info("Inside the insertDataFromJson method");
    }

    private void insertData() {
        log.info("Inside the insertData method");
        Employee john = Employee.builder()

                .firstName("John")
                .middleName("Zakaria")
                .mobileNumber("529853225")
                .email("john@farabi.ae")
                .employmentId(1)
                .extensionNumber(632)
                .employmentType(EmploymentType.PERMANENT)
                .build();
        Employee kamini = Employee.builder()
                .firstName("Kamini")
                .middleName("Krishna")
                .lastName("Menon")
                .mobileNumber("566333225")
                .email("kamini@farabi.ae")
                .extensionNumber(600)
                .employmentId(2)
                .employmentType(EmploymentType.INTERN)
                .build();


        Employee kalyan = Employee.builder()
                .firstName("Kalyan")
                .middleName("Lohitha")
                .lastName("Warrier")
                .mobileNumber("529852369")
                .email("kalyanfarabi.ae")
                .extensionNumber(623)
                .employmentId(3)
                .employmentType(EmploymentType.CONTRACT)
                .build();

        Employee peter = Employee.builder()
                .firstName("Peter")
                .lastName("Jones")
                .mobileNumber("236965325")
                .email("peter@farabi.a")
                .extensionNumber(609)
                .employmentId(4)
                .employmentType(EmploymentType.PERMANENT)
                .build();
        employeeRepository.saveAll(List.of(john, kalyan, kamini, peter));
    }
}
