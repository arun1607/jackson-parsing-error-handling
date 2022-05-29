package com.app.learning.controller;

import com.app.learning.model.Employee;
import com.app.learning.service.EmployeeService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @PostMapping
  public Employee create(@RequestBody Employee employee) {
    return employeeService.create(employee);
  }

  @GetMapping
  public List<Employee> getAll() {
    return employeeService.getAll();
  }
}
