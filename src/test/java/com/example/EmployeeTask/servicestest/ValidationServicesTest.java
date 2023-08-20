package com.example.EmployeeTask.servicestest;

import com.example.EmployeeTask.models.Employee;
import com.example.EmployeeTask.repos.EmployeeRepo;
import com.example.EmployeeTask.services.ValidationServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class ValidationServicesTest {

    @InjectMocks
    ValidationServices validationServices;
    @Mock
    EmployeeRepo employeeRepo;

    @Test
    void test_name_duplicated() {
        //Arrange
        Employee employee = new Employee(22, "Dina", 12, "Asmaa@gmail.com", 7212);
        //Act
        doReturn(employee).when(employeeRepo).findByName("Dina");
        boolean check = validationServices.nameExist("Dina", 23);
        //Assertion
        assertFalse(check);

    }

    @Test
    void test_name_unique_employee_null() {
        //Arrange
        Employee employee = new Employee(22, "Alaa", 12, "Asmaa@gmail.com", 7212);
        //Act
//        doReturn(null).when(employeeRepo).findByName("Dina");
        when(employeeRepo.findByName("Dina")).thenReturn(null);
        boolean check = validationServices.nameExist("Dina", 22);
        //Assertion
        assertTrue(check);
    }

    @Test
    void test_name_unique_employee_notNull() {
        //Arrange
        Employee employee = new Employee(22, "Dina", 12, "Asmaa@gmail.com", 7212);
        //Act
        doReturn(employee).when(employeeRepo).findByName("Alaa");
        boolean check = validationServices.nameExist("Alaa", 22);
        //Assertion
        assertEquals(true, check);

    }

    @Test
    void test_age_lessThan_50() {
        //Arrange
        int age = 12;
        //Act
        boolean check = validationServices.check_age_more_than50(age);
        //Assertion
        assertEquals(check, false);
    }

    @MethodSource("methodProvider")
    @ParameterizedTest
//    @Test
    void test_age_moreThan_50(int age) {
        //Arrange
        //Act
        boolean check = validationServices.check_age_more_than50(age);
        //Assertion
        assertFalse(check);
    }

    @Test
    void test_valid_mail() {
        String email = "Shaimaa@gmail.com";
        boolean check = validationServices.check_email_validation(email);
        assertEquals(check, true);
    }

    @Test
    void test_not_valid_mail() {
        //Arrange
        String email = "Shaimaagmail.com";
        //Act
        boolean check = validationServices.check_email_validation(email);
        //Assertion
        assertNotEquals(check, true);
    }

    static Stream<Arguments> methodProvider() {
        return Stream.of(
                Arguments.of(12),
                Arguments.of(20),
                Arguments.of(26)
        );
    }
}
