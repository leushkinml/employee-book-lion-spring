package com.skypro.service;

import com.skypro.exception.EmployeeNotFoundedException;
import com.skypro.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeServiceOut;
    @InjectMocks
    private DepartmentService departmentOut;

    private List<Employee> actualEmployees;

    @BeforeEach
    public void setUp() {
        Employee employeeTest1 = new Employee("FirstName", "FirstSurname", 1, 1000);
        Employee employeeTest2 = new Employee("SecondName", "SecondSurname", 2, 2000);
        Employee employeeTest3 = new Employee("ThirdName", "ThirdSurname", 3, 3000);
        actualEmployees = new ArrayList<>(List.of(employeeTest1, employeeTest2, employeeTest3));
        Mockito.when(employeeServiceOut.getAllEmployees()).thenReturn(actualEmployees);
    }
    @Test
        // getEmployeesMapByDepartment
    public void shouldReturnEmployeesMapByDepartment() {
        final Map<Integer, List<Employee>> actual =
                actualEmployees.stream()
                        .map(Employee::getDepartment).collect(Collectors.toSet())
                        .stream()
                        .collect(Collectors.toMap(dept -> dept, dept -> actualEmployees.stream()
                                .filter(e -> e.getDepartment() == dept)
                                .collect(Collectors.toList())));

        final Map<Integer, List<Employee>> expected = departmentOut.getEmployeesMapByDepartment();
        assertEquals(expected, actual);
    }
    @Test
        // getEmployeesInDepartment
    public void shouldReturnEmployeesInDepartment() {
        final int department = 1;
        final List<Employee> actual = actualEmployees
                .stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
        final Collection<Employee> expected = departmentOut.getEmployeesInDepartment(department);
        assertEquals(expected, actual);
    }
    @Test
        // getSalarySumByDepartment
    public void shouldReturnSalarySumByDepartment() {
        final int department = 1;
        final int actual = actualEmployees
                .stream()
                .filter(e -> e.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .sum();
        final int expected = departmentOut.getSalarySumByDepartment(department);
        assertEquals(expected, actual);
    }
    @Test
        // getSalaryMinByDepartment
    public void shouldReturnSalaryMinByDepartment() throws EmployeeNotFoundedException {
        final int department = 1;
        final int actual = actualEmployees
                .stream()
                .filter(e -> e.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .min().orElse(0);
        final int expected = departmentOut.getSalaryMinByDepartment(department);
        assertEquals(expected, actual);
    }
    @Test
        // getSalaryMaxByDepartment
    public void shouldReturnSalaryMaxByDepartment() throws EmployeeNotFoundedException{
        final int department = 1;
        final int actual = actualEmployees
                .stream()
                .filter(e -> e.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .max().orElse(0);
        final int expected = departmentOut.getSalaryMaxByDepartment(department);
        assertEquals(expected, actual);
    }
}