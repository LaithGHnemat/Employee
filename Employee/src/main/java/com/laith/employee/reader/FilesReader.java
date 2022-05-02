package com.laith.employee.reader;

import com.laith.employee.model.Employee;

import java.io.IOException;
import java.util.List;

public interface FilesReader {
    List<Employee> getEmployees() throws IOException;
}
