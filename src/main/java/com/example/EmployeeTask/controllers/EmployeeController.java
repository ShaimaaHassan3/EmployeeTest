package com.example.EmployeeTask.controllers;

import com.example.EmployeeTask.dtos.EmployeeDTO;
import com.example.EmployeeTask.models.Employee;
import com.example.EmployeeTask.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeServices employeeServices;

    @PostMapping(value = "/add")
    public EmployeeDTO add(@RequestBody EmployeeDTO employee) {
        return employeeServices.add(employee);
    }

    @DeleteMapping("delete/{name}")
    public String delete(@PathVariable("name") String name) {
        return employeeServices.delete(name);
    }

    @PutMapping("/update")
    public EmployeeDTO update(@RequestBody EmployeeDTO employee) {
        return employeeServices.update(employee);
    }

    @GetMapping("get/{id}")
    public EmployeeDTO get(@PathVariable("id") int id) {
        return employeeServices.get(id);
    }
}
