package com.vaishnavi.employeemanagement.controller;

import com.vaishnavi.employeemanagement.model.Employee;
import com.vaishnavi.employeemanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // POST /api/employees?departmentId=1
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee,
                                                    @RequestParam Long departmentId) {
        Employee created = employeeService.createEmployee(employee, departmentId);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // GET /api/employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // GET /api/employees/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    // PUT /api/employees/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,
                                                    @Valid @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
    }

    // DELETE /api/employees/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/employees/department/{departmentId}
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Employee>> getByDepartment(@PathVariable Long departmentId) {
        return ResponseEntity.ok(employeeService.getEmployeesByDepartment(departmentId));
    }

    // GET /api/employees/designation/{designation}
    @GetMapping("/designation/{designation}")
    public ResponseEntity<List<Employee>> getByDesignation(@PathVariable String designation) {
        return ResponseEntity.ok(employeeService.getEmployeesByDesignation(designation));
    }

    // GET /api/employees/search?minSalary=30000
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> getBySalary(@RequestParam Double minSalary) {
        return ResponseEntity.ok(employeeService.getEmployeesBySalaryGreaterThan(minSalary));
    }
}
