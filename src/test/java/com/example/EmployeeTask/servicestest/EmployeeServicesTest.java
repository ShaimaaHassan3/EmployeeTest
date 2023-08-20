package com.example.EmployeeTask.servicestest;

import com.example.EmployeeTask.dtos.EmployeeDTO;
import com.example.EmployeeTask.models.Employee;
import com.example.EmployeeTask.repos.EmployeeRepo;
import com.example.EmployeeTask.services.EmployeeServices;
import com.example.EmployeeTask.services.ValidationServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServicesTest {
    @Mock
    EmployeeRepo employeeRepo;
    @Mock
    ValidationServices validationServices;
    @Spy
    @InjectMocks
    EmployeeServices employeeServices;

    @Test
    void test_retrieve_employee() {
        //Arrange
        Employee employee = new Employee(22, "Asmaa", 12, "Asmaa@gmail.com", 7212);

        //Act
        doReturn(employee).when(employeeRepo).findById(anyInt());
        EmployeeDTO employeeDTO = employeeServices.get(22);

        //Assertion
        Assertions.assertEquals(employee.getId(), employeeDTO.getId());
        Assertions.assertEquals(employee.getName(), employeeDTO.getName());
        Assertions.assertEquals(employee.getEmail(), employeeDTO.getEmail());
        Assertions.assertEquals(employee.getAge(), employeeDTO.getAge());
        Assertions.assertEquals(employee.getSalary(), employeeDTO.getSalary());
    }

    @Test
    public void test_add_new_employee() {
        //Arrange
        Employee employee = new Employee(22, "Asmaa", 12, "Asmaa@gmail.com", 7212);
        EmployeeDTO employeeDTO = new EmployeeDTO();

        //Act
        doNothing().when(employeeServices).valid_employee(any(EmployeeDTO.class));
        doReturn(employee).when(employeeRepo).save(Mockito.any(Employee.class));
        employeeDTO = employeeServices.add(employeeDTO);

        //Assertion
        assertEquals(employeeDTO.getName(), employee.getName());

    }

    @Test
    void test_delete_employee() {
        //Arrange
        Employee employee = new Employee(22, "Asmaa", 12, "Asmaa@gmail.com", 7212);
        String name = "Shaimaa";
        //Act
        doReturn(true).when(validationServices).isExist(any(String.class));
        String message = employeeServices.delete(name);
        //Assertion
        assertDoesNotThrow(() -> employeeRepo.deleteByName(name));
    }

    @Test
    void test_update_employee() {
        //Arrange
        Employee updatedemployee = new Employee(22, "Dina", 12, "Asmaa@gmail.com", 7212);
        EmployeeDTO employeeDTO = new EmployeeDTO();

        //Act
        doNothing().when(employeeServices).valid_employee(any(EmployeeDTO.class));
        doReturn(updatedemployee).when(employeeRepo).save(Mockito.any(Employee.class));
        employeeDTO = employeeServices.update(employeeDTO);

        //Assertion
        assertEquals(updatedemployee.getName(), employeeDTO.getName());
        Assertions.assertEquals(updatedemployee.getId(), employeeDTO.getId());
        Assertions.assertEquals(updatedemployee.getName(), employeeDTO.getName());
        Assertions.assertEquals(updatedemployee.getEmail(), employeeDTO.getEmail());
        Assertions.assertEquals(updatedemployee.getAge(), employeeDTO.getAge());
        Assertions.assertEquals(updatedemployee.getSalary(), employeeDTO.getSalary());
    }

}
