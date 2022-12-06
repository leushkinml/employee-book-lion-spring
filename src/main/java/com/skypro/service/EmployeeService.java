package com.skypro.service;

import com.skypro.exception.EmployeeException;
import com.skypro.record.EmployeeRequest;
import com.skypro.model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service  // Аннотация Сервис говорит спрингу что этот компонент должен быть создан в единственном экземпляре
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();
    private final List<Employee> listEmployees = new ArrayList<>(employees.values());

    public Employee addEmployee(EmployeeRequest employeeRequest) throws EmployeeException {
        if (employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null) {
            throw new EmployeeException("The name must contain only letters");
        }

        Employee employee = new Employee(employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());
        this.employees.put(employee.getId(), employee);
        return employee;
    }
//    private void checkEmployee (EmployeeRequest employeeRequest) throws Exception {
//        if (
//                StringUtils.isBlank(employeeRequest.getFirstName())
//                        || StringUtils.isBlank(employeeRequest.getLastName())
//                        || StringUtils.isAlpha(employeeRequest.getFirstName())
//                        || StringUtils.isAlpha(employeeRequest.getLastName())) {
//            throw new IllegalArgumentException("Допустимы только буквы.");
//        }
//    }
    public Collection<Employee> getAllEmployees() {

        return this.employees.values();
    }
//    public List<Employee> getAllEmployees() {
//        return employees.values().stream().toList();
//    }
    public int getSalarySum() {
        return employees.values().stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }
    //    public int getSalarySum() {
//        int sum = 0;
//        for (Employee employee : employees.values()) {
//            int salary = employee.getSalary();
//            sum += salary;
//        }
//        return sum;
//    }
    public OptionalDouble getSalaryAverage()    {
        return employees.values().stream()
                .mapToDouble(Employee::getSalary)
                .average();
    }

    public Employee getSalaryMin() throws EmployeeException {
        return employees.values().stream()
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(()->new EmployeeException("The must be at list one date"));
        //return employees.values().stream().min(Comparator.comparingInt(Employee::getSalary)).orElse(null);
    }
//    public Optional<Employee> getSalaryMin() {
//        return employees.values().stream().min(Comparator.comparingInt(Employee::getSalary));
//        //return employees.values().stream().min(Comparator.comparingInt(e -> e.getSalary()));
//    }
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
    public Employee getSalaryMax() throws EmployeeException {
        return employees.values()
                .stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(()->new EmployeeException("The must be at list one date"));
        //return employees.values().stream().max(Comparator.comparingInt(Employee::getSalary)).orElse(null);
    }
//    public Optional<Employee> getSalaryMax() {
//        return employees.values().stream().max(Comparator.comparingInt(Employee::getSalary));
//        //return employees.values().stream().max(Comparator.comparingInt(e -> e.getSalary()));
//    }
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
    public List<Employee> getSalaryHigh() {
    double averageSalary = employees.values().stream()
            .mapToInt(Employee::getSalary)
            .average()
            .getAsDouble();
    return employees.values().stream()
            .filter(e -> e.getSalary()>averageSalary)
            .toList();
    }
//    public List<Employee> getSalaryHigh() {
//        List<Employee> listEmployees = new ArrayList<>(employees.values());
//        List<Employee> employeesWithSalaryMoreAverage = new ArrayList<>();
//        double averageSalary = 0;
//        int summary = 0;
//
//        for (int i = 0; i < listEmployees.size(); i++) {
//            if (listEmployees.get(i) != null) {
//                summary += listEmployees.get(i).getSalary();
//            }
//        }
//        averageSalary = summary / (double) listEmployees.size();
//
//         for (int i = 0; i < listEmployees.size(); i++) {
//            if (listEmployees.get(i) != null) {
//                if (listEmployees.get(i).getSalary() > averageSalary) {
//                    employeesWithSalaryMoreAverage.add(listEmployees.get(i));
//                }
//            }
//        }
//        return employeesWithSalaryMoreAverage;
//    }

    public Employee removeEmployee(int id) {
        return employees.remove(id);
    }
}
