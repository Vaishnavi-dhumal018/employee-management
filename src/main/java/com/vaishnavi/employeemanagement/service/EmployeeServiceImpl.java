package com.vaishnavi.employeemanagement.service;

import com.vaishnavi.employeemanagement.exception.DepartmentNotFoundException;
import com.vaishnavi.employeemanagement.exception.DuplicateEmailException;
import com.vaishnavi.employeemanagement.exception.EmployeeNotFoundException;
import com.vaishnavi.employeemanagement.model.Department;
import com.vaishnavi.employeemanagement.model.Employee;
import com.vaishnavi.employeemanagement.repository.DepartmentRepository;
import com.vaishnavi.employeemanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                                DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public Employee createEmployee(Employee employee, Long departmentId) {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new DuplicateEmailException(employee.getEmail());
        }

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentId));

        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    @Transactional
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        existing.setName(updatedEmployee.getName());
        existing.setEmail(updatedEmployee.getEmail());
        existing.setPhone(updatedEmployee.getPhone());
        existing.setDesignation(updatedEmployee.getDesignation());
        existing.setSalary(updatedEmployee.getSalary());
        existing.setJoiningDate(updatedEmployee.getJoiningDate());

        return employeeRepository.save(existing);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException(id);
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getEmployeesByDepartment(Long departmentId) {
        if (!departmentRepository.existsById(departmentId)) {
            throw new DepartmentNotFoundException(departmentId);
        }
        return employeeRepository.findByDepartmentId(departmentId);
    }

    @Override
    public List<Employee> getEmployeesByDesignation(String designation) {
        return employeeRepository.findByDesignationIgnoreCase(designation);
    }

    @Override
    public List<Employee> getEmployeesBySalaryGreaterThan(Double minSalary) {
        return employeeRepository.findBySalaryGreaterThanEqual(minSalary);
    }
}
