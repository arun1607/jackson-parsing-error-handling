package com.app.learning.service;

import com.app.learning.model.Employee;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  public List<Employee> getAll() {
    List<Employee> employeeList = new ArrayList<>();
    Employee employee1 = new Employee("John", "Wick");
    Employee employee2 = new Employee("Jason", "Bourne");
    employeeList.add(employee1);
    employeeList.add(employee2);
    return employeeList;
  }

  public Employee create(Employee employee) {
    return new Employee("One " + employee.getFirstName(), "Two " + employee.getLastName());
  }
}
