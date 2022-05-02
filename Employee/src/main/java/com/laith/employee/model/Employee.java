package com.laith.employee.model;

import com.laith.employee.enums.EmploymentType;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Employee {
    @Id
    private int employmentId;
    @Column(nullable = false)
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private int extensionNumber;
    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;
}
