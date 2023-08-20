package com.example.EmployeeTask.repos;

import com.example.EmployeeTask.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>, QueryByExampleExecutor<Employee> {
    Employee findByName(String name);

    Employee findById(int id);

    boolean getEmployeeByName(String name);

    void deleteByName(String name);
}
