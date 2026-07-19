package com.vaishnavi.employeemanagement.repository;

import com.vaishnavi.employeemanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartmentId(Long departmentId);

    List<Employee> findByDesignationIgnoreCase(String designation);

    List<Employee> findBySalaryGreaterThanEqual(Double minSalary);

    boolean existsByEmail(String email);
}
