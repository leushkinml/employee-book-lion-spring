package com.skypro.service;

import com.skypro.exception.EmployeeNotFoundedException;
import com.skypro.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private Stream<Employee> getEmployeesByDepartmentStream(int department) {
        return employeeService.getAllEmployees().stream().filter(e -> e.getDepartment() == department);
    }

        public Map<Integer, List<Employee>> getEmployeesMapByDepartment() {
        return employeeService.getAllEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public Collection<Employee> getEmployeesInDepartment(int department){
        // return getEmployeesByDepartmentStream(department).collect(Collectors.toList());
        return employeeService.getAllEmployees()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public int getSalarySumByDepartment(int department) {
        return getEmployeesByDepartmentStream(department)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public int getSalaryMinByDepartment(int department) throws EmployeeNotFoundedException  {
        return getEmployeesByDepartmentStream(department)
                .mapToInt(Employee::getSalary).min()
                .orElseThrow(()->new EmployeeNotFoundedException("Employee is not founded"));
    }

    public int getSalaryMaxByDepartment(int department) throws EmployeeNotFoundedException  {
        return getEmployeesByDepartmentStream(department)
                .mapToInt(Employee::getSalary).max()
                .orElseThrow(()->new EmployeeNotFoundedException("Employee is not founded"));
    }
}
