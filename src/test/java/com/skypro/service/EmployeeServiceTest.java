package com.skypro.service;

import com.skypro.exception.EmployeeException;
import com.skypro.model.Employee;
import com.skypro.record.EmployeeRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class EmployeeServiceTest {

    private EmployeeService out;


    @BeforeEach
    public void setUp() {
        this.out = new EmployeeService();
        Stream.of(
                new EmployeeRequest("FirstName", "FirstSurname", 1, 1000),
                new EmployeeRequest("SecondName", "SecondSurname", 2, 2000),
                new EmployeeRequest("ThirdName", "ThirdSurname", 3, 3000),
                new EmployeeRequest("ForthName", "ForthSurname", 4, 4000),
                new EmployeeRequest("FivesName", "FivesSurname", 5, 5000)
        ).forEach(out::addEmployee);
    }

    @BeforeEach
    public void setUp() {
        this.out = new EmployeeService();
        Stream.of(
                new Employee("FirstName", "FirstSurname", 1, 1000),
                new Employee("SecondName", "SecondSurname", 2, 2000),
                new Employee("ThirdName", "ThirdSurname", 3, 3000),
                new Employee("ForthName", "ForthSurname", 4, 4000),
                new Employee("FivesName", "FivesSurname", 5, 5000)
        ).forEach(out::addEmployee);
    }


    @Test
    public void addEmployee() throws EmployeeException {
        EmployeeRequest request = new EmployeeRequest("Valid", "Valid", 3, 3000);
        Employee result = out.addEmployee(request);
        assertEquals(request.getFirstName(), result.getFirstName());
        assertEquals(request.getLastName(), result.getLastName());
        assertEquals(request.getDepartment(), result.getDepartment());
        assertEquals(request.getSalary(), result.getSalary());
        Assertions
                .assertThat(out.getAllEmployees())
                .contains(result);

    }

    @Test // getAllEmployees
    public void shouldReturnAllEmployees() {
        Collection<Employee> employees = out.getAllEmployees();
        Assertions.assertThat(employees).hasSize(5);
        Assertions.assertThat(employees).hasSize(5)
                .first()
                .extractig(Employee::getFirstName)
                .isEqualTo(15000);
    }

    @Test // getSalarySum
    public void shouldReturnSalarySum() {
        int sum = out.getSalarySum();
        Assertions
                .assertThat(sum)
                .isEqualTo(15000);
    }

    @Test // getSalaryAverage
    public void shouldReturnSalaryAverage() {
        OptionalDouble average = out.getSalaryAverage();
        Assertions
                .assertThat(average)
                .isEqualTo(3000);
    }


    @Test // getSalaryMin
    public void shouldReturnEmployeeWithSalaryMin() throws EmployeeException{
    Employee employee = out.getSalaryMin();
        Assertions.assertThat(employee)
                .isNotNull()
                .extracting(Employee::getFirstName)
                .isEqualTo("FirstName");
    }

    @Test // getSalaryMax
    public void shouldReturnEmployeeWithSalaryMax() throws EmployeeException {
    Employee employee = out.getSalaryMax();
        Assertions.assertThat(employee)
                .isNotNull()
                .extracting(Employee::getFirstName)
                .isEqualTo("FivesName");
    }

    @Test
    public void getSalaryHigh() {
    }

    @Test
    public void removeEmployee() {
        out.removeEmployee(out.getAllEmployees().iterator().next().getId());
        Collection<Employee> employees = out.getAllEmployees();
        Assertions.assertThat(employees).hasSize(4);
    }
}