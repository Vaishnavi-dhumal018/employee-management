package com.vaishnavi.employeemanagement.service;

import com.vaishnavi.employeemanagement.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee, Long departmentId);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);

    List<Employee> getEmployeesByDepartment(Long departmentId);

    List<Employee> getEmployeesByDesignation(String designation);

    List<Employee> getEmployeesBySalaryGreaterThan(Double minSalary);
}
