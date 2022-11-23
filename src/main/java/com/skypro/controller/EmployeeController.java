package com.skypro.controller;


import com.skypro.model.Employee;
import com.skypro.record.EmployeeRequest;
import com.skypro.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 * HTTP Методы (основные, которые мы используем):
 * GET - для получения ресурса или набора ресурсов (например вернуть всех сотрудников)
 * POST - для создания ресурса (создать нового сотрудника)
 * PUT - полная модификация ресурса (заменить все поля у сотрудника)
 * PATCH - частичная модификация (поменять зарплату)
 * DELETE - удаление ресурса
 */

@RestController // Эта аннотация реализует принцип Рест. Можно использовать несколько клиентов
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    // Принцип инъекции зависимости. Мы не создаём цепочку зависимости, а просим спринг добавить EmployeeService в качестве зависимости
    // Объявим конструктор для нашего контроллера, и в нём в качестве аргумента EmployeeService employeeService,
    // и назначим нашему полю значение из аргумента

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return this.employeeService.addEmployee(employeeRequest);
    }

    @GetMapping("/helloWorld")
    public String showHelloWorld() {
        return "Hello, world!";
    }

    @GetMapping("/employees") // Эта аннотация, которая вернёт наших сотрудников по запоросу /employees
    public Collection<Employee> getAllEmployees() {// Создали метод для получения наших сотрудников.
        return this.employeeService.getAllEmployees();
    }

    @GetMapping("/employees/salary/sum")
    public int getSalarySum() {
        return this.employeeService.getSalarySum();
    }

    @GetMapping("/employees/salary/min")
    public Employee getSalaryMin() {
        return this.employeeService.getSalaryMin();
    }

    @GetMapping("/employees/salary/max")
    public Employee getSalaryMax() {
        return this.employeeService.getSalaryMax();
    }

    @GetMapping("/employees/salary/high-salary")
    public List<Employee> getSalaryHigh() {
        return this.employeeService.getSalaryHigh();
    }
}
