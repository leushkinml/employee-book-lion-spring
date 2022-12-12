package com.skypro.service;

import com.skypro.exception.EmployeeException;
import com.skypro.model.Employee;
import com.skypro.record.EmployeeRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    //    @Mock
//    private EmployeeRequest employeeRequestOut;
//    @InjectMocks
    private EmployeeService employeeOut;
    private List<Employee> actualEmployees;
//    private final Map<Integer, Employee> employeesTest = new HashMap<>();

//    @BeforeEach
//    public void setUp() {
//        this.employeeOut = new EmployeeService();
//        EmployeeService employeeOut = new EmployeeService();
//        Employee employeeTest1 = new Employee("FirstName", "FirstSurname", 1, 1000);
//        Employee employeeTest2 = new Employee("SecondName", "SecondSurname", 2, 2000);
//        Employee employeeTest3 = new Employee("ThirdName", "ThirdSurname", 3, 3000);
//        Employee employeeTest4 = new Employee("ForthName", "ForthSurname", 4, 4000);
//        Employee employeeTest5 = new Employee("FivesName", "FivesSurname", 5, 5000);
//
//        actualEmployees = new ArrayList<>(List.of(employeeTest1, employeeTest2, employeeTest3, employeeTest4, employeeTest5));
//
//        this.employeesTest.put(0, employeeTest1);
//        this.employeesTest.put(1, employeeTest2);
//        this.employeesTest.put(2, employeeTest3);
//        this.employeesTest.put(3, employeeTest4);
//        this.employeesTest.put(4, employeeTest5);
//
//        final Collection<Employee> employeeEx = new ArrayList<>(employeesTest.values());
//
//        //Mockito.when(employeeRequestOut.getListEmployees()).thenReturn(actualEmployees);
//        //Mockito.when(employeeRequestOut.employees).thenReturn(employeesTest);
//    }

    @BeforeEach
    public void setUpExpectedEmployees() throws EmployeeException {

        this.employeeOut = new EmployeeService(); // создаем сервис

        EmployeeRequest employeeRequest1 = new EmployeeRequest("FirstName", "FirstSurname", 1, 1000);
        EmployeeRequest employeeRequest2 = new EmployeeRequest("SecondName", "SecondSurname", 2, 2000);
        EmployeeRequest employeeRequest3 = new EmployeeRequest("ThirdName", "ThirdSurname", 3, 3000);
        EmployeeRequest employeeRequest4 = new EmployeeRequest("ForthName", "ForthSurname", 4, 4000);
        EmployeeRequest employeeRequest5 = new EmployeeRequest("FivesName", "FivesSurname", 5, 5000);

        employeeOut.addEmployee(employeeRequest1);   // добавляем сотрудников в сервис
        employeeOut.addEmployee(employeeRequest2);
        employeeOut.addEmployee(employeeRequest3);
        employeeOut.addEmployee(employeeRequest4);
        employeeOut.addEmployee(employeeRequest5);

        actualEmployees = new ArrayList<>(employeeOut.getAllEmployees());

        //actualEmployees = new ArrayList<>(List.of(employeeRequest1, employeeRequest2, employeeRequest3, employeeRequest4, employeeRequest5));
    }

//    @BeforeEach
//    public void setUpActualEmployees() throws EmployeeException {
//
//        Employee employeeTest1 = new Employee("FirstName", "FirstSurname", 1, 1000);
//        Employee employeeTest2 = new Employee("SecondName", "SecondSurname", 2, 2000);
//        Employee employeeTest3 = new Employee("ThirdName", "ThirdSurname", 3, 3000);
//        Employee employeeTest4 = new Employee("ForthName", "ForthSurname", 4, 4000);
//        Employee employeeTest5 = new Employee("FivesName", "FivesSurname", 5, 5000);
//
//        actualEmployees = new ArrayList<>(List.of(employeeTest1, employeeTest2, employeeTest3, employeeTest4, employeeTest5));
//    }

//    @BeforeEach
//    public void setUp() throws EmployeeException {
//        this.employeeOut = new EmployeeService();
//
//        for (EmployeeRequest request : Arrays.asList(
//                new EmployeeRequest("FirstName", "FirstSurname", 1, 1000),
//                new EmployeeRequest("SecondName", "SecondSurname", 2, 2000),
//                new EmployeeRequest("ThirdName", "ThirdSurname", 3, 3000),
//                new EmployeeRequest("ForthName", "ForthSurname", 4, 4000),
//                new EmployeeRequest("FivesName", "FivesSurname", 5, 5000)
//        )) {
//            employeeOut.addEmployee(request);
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
        assertEquals(employeeOut.getAllEmployees().stream().toList(), actualEmployees);
    }

    //    @Test // getAllEmployees
//    public void shouldReturnAllEmployees() {
//        Collection<Employee> employeeTem = employeeOut.getAllEmployees();
//        List<Employee> employeeExpected = new ArrayList<>(employeeTem);
//        assertEquals(employeeExpected, actualEmployees);
//    }
    @Test // getSalarySum
    public void shouldReturnSalarySum() {
        int sumActual = actualEmployees
                .stream()
                .mapToInt(Employee::getSalary)
                .sum();

        int sumExpected = employeeOut.getSalarySum();

        assertEquals(sumExpected, sumActual);
    }

    @Test // getSalaryAverage
    public void shouldReturnSalaryAverage() {
        OptionalDouble averageActual = actualEmployees
                .stream()
                .mapToDouble(Employee::getSalary)
                .average();
        OptionalDouble averageExpected = employeeOut.getSalaryAverage();
        assertEquals(averageExpected, averageActual);
    }

    @Test // getSalaryMin
    public void shouldReturnEmployeeWithSalaryMin() throws EmployeeException {
        Employee employeeActual = actualEmployees
                .stream()
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeException("The must be at list one date"));
        Employee employeeExpected = employeeOut.getSalaryMin();
        assertEquals(employeeExpected, employeeActual);
    }

    @Test // getSalaryMax
    public void shouldReturnEmployeeWithSalaryMax() throws EmployeeException {
        Employee employeeActual = actualEmployees
                .stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeException("The must be at list one date"));
        Employee employeeExpected = employeeOut.getSalaryMax();
        assertEquals(employeeExpected, employeeActual);
    }

    @Test // getSalaryHigh
    public void shouldReturnEmployeeWithSalaryMoreThenAverage() {
        double averageSalaryExpected = actualEmployees
                .stream()
                .mapToInt(Employee::getSalary)
                .average()
                .getAsDouble();
        List<Employee> employeeActual = actualEmployees
                .stream()
                .filter(e -> e.getSalary() > averageSalaryExpected)
                .toList();
        List<Employee> employeeExpected = employeeOut.getSalaryHigh();
        assertEquals(employeeExpected, employeeActual);
    }

    @Test // removeEmployee
    public void shouldReturnEmployeeListWithoutRemovedEmployee() {
//        employeeOut.removeEmployee(employeeOut.getAllEmployees().iterator().next().getId());
//        Collection<Employee> employees = employeeOut.getAllEmployees();
//        Assertions.assertTrue((BooleanSupplier) hasSize(4));
//        employeeOut.removeEmployee(2);
//        Collection<Employee> employeeExpected = new ArrayList<>(employeesTest.values());

//        int index = 2;
//        actualEmployees.remove(index);
//        List<Employee> employeeActual = new ArrayList<>(actualEmployees);
//        employeeOut.removeEmployee(index);
//        assertEquals(employeeOut.getAllEmployees().stream().toList(), employeeActual);

        int removeEmployeeId = 2;
        employeeOut.removeEmployee(removeEmployeeId);
        List<Employee> employees = employeeOut.getAllEmployees().stream().toList();
        Assertions.assertFalse(employees.stream().anyMatch(it -> it.getId() == removeEmployeeId));
    }
}