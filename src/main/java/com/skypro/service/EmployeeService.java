package com.skypro.service;

import com.skypro.record.EmployeeRequest;
import com.skypro.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service  // Аннотация Сервис говорит спрингу что этот компонент должен быть создан в единственном экземпляре
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();
    private final List<Employee> listEmployees = new ArrayList<>(employees.values());

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if (employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null) {
            throw new IllegalArgumentException("Employee name should be set");
        }

        Employee employee = new Employee(employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());
        this.employees.put(employee.getId(), employee);
        return employee;
    }

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    public int getSalarySum() {
        return employees.values().stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public OptionalDouble getSalaryAverage() {
        return employees.values().stream()
                .mapToDouble(Employee::getSalary)
                .average();
    }
//    public int getSalarySum() {
//        int sum = 0;
//        for (Employee employee : employees.values()) {
//            int salary = employee.getSalary();
//            sum += salary;
//        }
//        return sum;
//    }

    public Optional<Employee> getSalaryMin() {
        return employees.values().stream().min(Comparator.comparingInt(e -> e.getSalary()));
    }
// для стримов есть встроенные методы min и max
// которые принимают компаратор - специальный объект который сравнивает два значения Employee -
// создать его можно через Comparator.comparingInt(e -> e.getSalary()) -
// то есть тогда сотрудники будут сравниваться по зарплате

//    public Employee getSalaryMin() {
//        List<Employee> listEmployees = new ArrayList<>(employees.values());
//        Employee staffWithMin = null;
//        int minSalary = 1000000000;
//        int i;
//        for (i = 0; i < listEmployees.size(); i++) {
//            if (listEmployees.get(i) != null) {
//                if (listEmployees.get(i).getSalary() != 0) {
//                    if (listEmployees.get(i).getSalary() < minSalary) {
//                        minSalary = listEmployees.get(i).getSalary();
//                        staffWithMin = listEmployees.get(i);
//                    }
//                }
//            }
//        }
//        return staffWithMin;
//    }

    public Optional<Employee> getSalaryMax() {
        return employees.values().stream().max(Comparator.comparingInt(e -> e.getSalary()));
    }
//    public Employee getSalaryMax() {
//        List<Employee> listEmployees = new ArrayList<>(employees.values());
//        Employee staffWithMax = null;
//        int maxSalary = -1;
//        for (int i = 0; i < listEmployees.size(); i++) {
//            if (listEmployees.get(i) != null) {
//                    if (listEmployees.get(i).getSalary() > maxSalary) {
//                        maxSalary = listEmployees.get(i).getSalary();
//                        staffWithMax = listEmployees.get(i);
//                    }
//            }
//        }
//        return staffWithMax;
//    }

    public List<Employee> getSalaryHigh() {
        List<Employee> listEmployees = new ArrayList<>(employees.values());
        List<Employee> employeesWithSalaryMoreAvarage = new ArrayList<>();
        double avarageSalary = 0;
        int summary = 0;

        for (int i = 0; i < listEmployees.size(); i++) {
            if (listEmployees.get(i) != null) {
                summary += listEmployees.get(i).getSalary();
            }
        }
        avarageSalary = summary / (double) listEmployees.size();

        Employee staffWithAvarage = null;

         for (int i = 0; i < listEmployees.size(); i++) {
            if (listEmployees.get(i) != null) {
                if (listEmployees.get(i).getSalary() > avarageSalary) {
                    employeesWithSalaryMoreAvarage.add(listEmployees.get(i));
                }
            }
        }
        return employeesWithSalaryMoreAvarage;
    }

//    public List<Employee> getSalaryHigh() {
//        List<Employee> listEmployees = new ArrayList<>(employees.values());
//        List<Employee> employeesWithSalaryMoreAvarage = new ArrayList<>();
//
//        OptionalDouble avarageSalary = employees.values().stream()
//                .mapToInt(Employee::getSalary)
//                .average();
//
//        Employee staffWithAvarage = null;
//
//        for (int i = 0; i < listEmployees.size(); i++) {
//            if (listEmployees.get(i) != null) {
//                if (listEmployees.get(i).getSalary() > avarageSalary) {
//                    employeesWithSalaryMoreAvarage.add(listEmployees.get(i));
//                }
//            }
//        }
//        return employeesWithSalaryMoreAvarage;
//    }
}
