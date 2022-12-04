package com.skypro.controller;

import com.skypro.exception.EmployeeException;
import com.skypro.exception.EmployeeNotFoundedException;
import com.skypro.model.Employee;
import com.skypro.record.EmployeeRequest;
import com.skypro.service.DepartmentService;
import com.skypro.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

@Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/department/{id}/employees") // Эта аннотация, которая вернёт наших сотрудников по запоросу /employees
    public ResponseEntity<Collection<Employee>> getAllEmployeesInDepartment(@PathParam(value = "id") int department) {// Создали метод для получения наших сотрудников.
        return new ResponseEntity<>(this.departmentService.getEmployeesInDepartment((department)), HttpStatus.OK);
    }

//    @GetMapping("/department/employees")
//    public ResponseEntity<Map<Integer, List<Employee>> getEmployeesMapByDepartment() {
//        return new ResponseEntity<>(this.departmentService.getEmployeesMapByDepartment(), HttpStatus.OK);
//    }

    @GetMapping("/department/{id}/salary/sum")
    public ResponseEntity<Integer> getSalarySumByDepartment(@PathParam(value = "id") int department) {
        return new ResponseEntity<>(this.departmentService.getSalarySumByDepartment(department), HttpStatus.OK);
    }

    @GetMapping("/department/{id}/salary/min")
    public ResponseEntity<Integer> getSalaryMinByDepartment(@PathParam(value = "id") int department) throws EmployeeNotFoundedException {
        return new ResponseEntity<>(this.departmentService.getSalaryMinByDepartment(department), HttpStatus.OK);
    }

    @GetMapping("/department/{id}/salary/max")
    public ResponseEntity<Integer> getSalaryMaxByDepartment(@PathParam(value = "id") int department) throws EmployeeNotFoundedException {
        return new ResponseEntity<>(this.departmentService.getSalaryMaxByDepartment(department), HttpStatus.OK);
    }

}
