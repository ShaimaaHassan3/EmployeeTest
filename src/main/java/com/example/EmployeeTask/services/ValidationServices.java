package com.example.EmployeeTask.services;

import com.example.EmployeeTask.dtos.EmployeeDTO;
import com.example.EmployeeTask.models.Employee;
import com.example.EmployeeTask.repos.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ValidationServices {
    private final EmployeeRepo employeeRepo;

    public boolean check_email_validation(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean check_age_more_than50(int age) {
        return age > 50;
    }

    public boolean nameExist(String name, int id) {
        Employee employee = employeeRepo.findByName(name);
        if (employee == null)
            return true;
       else if (employee.getId() == id)
            return true;
        return false;
    }

    public boolean isExist(String name) {
        return employeeRepo.getEmployeeByName(name);
    }



}
