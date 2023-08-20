package com.example.EmployeeTask.services;

import com.example.EmployeeTask.dtos.EmployeeDTO;
import com.example.EmployeeTask.models.Employee;
import com.example.EmployeeTask.repos.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class EmployeeServices {
    private final EmployeeRepo employeeRepo;
    private final ValidationServices validationServices;
    ModelMapper modelMapper = new ModelMapper();

    public EmployeeDTO add(EmployeeDTO employeeDTO) {
        valid_employee(employeeDTO);
        System.out.println(employeeDTO);
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        employee = employeeRepo.save(employee);
        System.out.println("Emp :" + employee);
        EmployeeDTO employee1 = modelMapper.map(employee, EmployeeDTO.class);
        return employee1;
    }

    @Transactional
    public String delete(String name) {
        System.out.println("Name : " + name);
        if (!validationServices.isExist(name))
            throw new RuntimeException("Employee Not Exist");
        employeeRepo.deleteByName(name);
        return "Deleted Successfully";
    }

    public EmployeeDTO update(EmployeeDTO employeeDTO) {
        valid_employee(employeeDTO);
        System.out.println(employeeDTO);
        EmployeeDTO employee1 = modelMapper.map(employeeRepo.save(modelMapper.map(employeeDTO, Employee.class)), EmployeeDTO.class);
        return employee1;
    }

    public EmployeeDTO get(int id) {
        Employee employee = employeeRepo.findById(id);
        if (employee == null)
            throw new NullPointerException("This Id Not Exist");
        return modelMapper.map(employee, EmployeeDTO.class);
    }
    public void valid_employee(EmployeeDTO employeeDTO) {
        if (!validationServices.nameExist(employeeDTO.getName(), employeeDTO.getId()))
            throw new RuntimeException("This Name Exist");
        else if (validationServices.check_age_more_than50(employeeDTO.getAge())) {
            throw new RuntimeException("Age More Than 50 Not allowed");
        } else if (!validationServices.check_email_validation(employeeDTO.getEmail())) {
            throw new RuntimeException("Email Not Valid");
        }
    }

}
