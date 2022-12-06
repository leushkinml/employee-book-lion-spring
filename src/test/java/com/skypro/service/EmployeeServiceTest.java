package com.skypro.service;
import com.skypro.exception.EmployeeException;
import com.skypro.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeServiceTest {

    private EmployeeService employeeOut;

    private List<Employee> actualEmployees;

    private final Map<Integer, Employee> employees = new HashMap<>();

    @BeforeEach
    public void setUp() {
        this.employeeOut = new EmployeeService();
        EmployeeService employeeService = employeeOut;
        Employee employeeTest1 = new Employee("FirstName", "FirstSurname", 1, 1000);
        Employee employeeTest2 = new Employee("SecondName", "SecondSurname", 2, 2000);
        Employee employeeTest3 = new Employee("ThirdName", "ThirdSurname", 3, 3000);
        Employee employeeTest4 = new Employee("ForthName", "ForthSurname", 4, 4000);
        Employee employeeTest5 = new Employee("FivesName", "FivesSurname", 5, 5000);

        actualEmployees = new ArrayList<>(List.of(employeeTest1, employeeTest2, employeeTest3, employeeTest4, employeeTest5));

        this.employees.put(0, employeeTest1);
        this.employees.put(1, employeeTest2);
        this.employees.put(2, employeeTest3);
        this.employees.put(3, employeeTest4);
        this.employees.put(4, employeeTest5);
        final Collection<Employee> employeeEx = new ArrayList<>(employees.values());
    }
//    @BeforeEach
//    public void setUp() {
//        this.employeeOut = new EmployeeService();
//        EmployeeService employeeService = employeeOut;
//
//        for (EmployeeRequest request : Arrays.asList(
//                new EmployeeRequest("FirstName", "FirstSurname", 1, 1000),
//                new EmployeeRequest("SecondName", "SecondSurname", 2, 2000),
//                new EmployeeRequest("ThirdName", "ThirdSurname", 3, 3000),
//                new EmployeeRequest("ForthName", "ForthSurname", 4, 4000),
//                new EmployeeRequest("FivesName", "FivesSurname", 5, 5000)
//        )) {
//            employeeService.addEmployee(request);
//        }
//    }

//    @Test
//    public void addEmployee() throws EmployeeException {
//        EmployeeRequest request = new EmployeeRequest("Valid", "Valid", 3, 3000);
//        Employee result = employeeOut.addEmployee(request);
//        assertEquals(request.getFirstName(), result.getFirstName());
//        assertEquals(request.getLastName(), result.getLastName());
//        assertEquals(request.getDepartment(), result.getDepartment());
//        assertEquals(request.getSalary(), result.getSalary());
//
//        assertEquals(result, actualEmployees);
//        Assertions
//                .assertThat(employeeOut.getAllEmployees())
//                .contains(result);
//    }

//    @Test // getAllEmployees
//    public void shouldReturnAllEmployees() {
//        Collection<Employee> employees = employeeOut.getAllEmployees();
//        Assertions.assertThat(employees).hasSize(5)
//                .first()
//                .extractig(Employee::getFirstName)
//                .isEqualTo(15000);
//    }

    @Test // getAllEmployees
    public void shouldReturnAllEmployees() {
        Collection<Employee> employeeExpected = employeeOut.employees.values();

        assertEquals(employeeExpected, actualEmployees);
    }

    @Test // getSalarySum
    public void shouldReturnSalarySum() {
        int sumActual = employees.values().stream()
                .mapToInt(Employee::getSalary)
                .sum();
        int sumExpected = employeeOut.getSalarySum();

        assertEquals(sumExpected, sumActual);
    }

    @Test // getSalaryAverage
    public void shouldReturnSalaryAverage() {
        OptionalDouble averageActual = employees.values().stream()
                .mapToDouble(Employee::getSalary)
                .average();
        OptionalDouble averageExpected = employeeOut.getSalaryAverage();
        assertEquals(averageExpected, averageActual);
    }

    @Test // getSalaryMin
    public void shouldReturnEmployeeWithSalaryMin() throws EmployeeException{
        Employee employeeActual = employees.values().stream()
            .min(Comparator.comparingInt(Employee::getSalary))
            .orElseThrow(()->new EmployeeException("The must be at list one date"));
        Employee employeeExpected = employeeOut.getSalaryMin();
        assertEquals(employeeExpected, employeeActual);
    }

    @Test // getSalaryMax
    public void shouldReturnEmployeeWithSalaryMax() throws EmployeeException {
        Employee employeeActual = employees.values().stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(()->new EmployeeException("The must be at list one date"));
        Employee employeeExpected = employeeOut.getSalaryMax();
        assertEquals(employeeExpected, employeeActual);
    }

    @Test // getSalaryHigh
    public void shouldReturnEmployeeWithSalaryMoreThenAverage() {
        double averageSalaryExpected = employees.values().stream()
                .mapToInt(Employee::getSalary)
                .average()
                .getAsDouble();
        List<Employee> employeeActual = employees.values().stream()
                .filter(e -> e.getSalary()>averageSalaryExpected)
                .toList();
        List<Employee> employeeExpected = employeeOut.getSalaryHigh();
        assertEquals(employeeExpected, employeeActual);
    }

    @Test // removeEmployee
    public void shouldReturnEmployeeListWithoutRemovedEmployee() {
//        employeeOut.removeEmployee(employeeOut.getAllEmployees().iterator().next().getId());
//        Collection<Employee> employees = employeeOut.getAllEmployees();
//        Assertions.assertTrue((BooleanSupplier) hasSize(4));
        employeeOut.removeEmployee(2);
        Collection<Employee> employeeExpected = new ArrayList<>(employees.values());
        actualEmployees.remove(2);
        Collection<Employee> employeeActual = new ArrayList<>(actualEmployees);
        assertEquals(employeeExpected, employeeActual);
    }
}