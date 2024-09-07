package pro.sky.Employee.Book.and.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    List<Employee> employees = List.of(
            new Employee("Petr", "Petrov", 26_754, 1),
            new Employee("Dima", " Dmitriev", 34_434, 1),
            new Employee("Maxim", "Maximov", 87_543, 2),
            new Employee("Nadya", "Ivanova", 30_120, 2),
            new Employee("Luda", "Lidovskya", 25_430, 3),
            new Employee("Alex", "Alexeev", 28_540, 3),
            new Employee("Vanya", "Vanovich", 24_240, 4),
            new Employee("Luba", "Super", 20_565, 4),
            new Employee("Anna", "Marchyk", 18_110, 5),
            new Employee("Dima", "Clayd", 17_590, 5));


    @Test
    @DisplayName("Возвращает сотрудника с максимальной зарплатой")
    void maxSalary () {
        int departmentId = 1;
        when(employeeService.allEmployees()).thenReturn(employees);
        Employee expected = employees.stream()
                .filter(employee -> departmentId == employee.getDepartmentId())
                .max(Comparator.comparingInt(e -> e.getSalary()))
                .orElse(null);
        //test
        Employee actual = departmentService.maxSalary(departmentId);
        // check
        assertThat(expected).isEqualTo(actual);
    }
    @Test
    @DisplayName("Возвращает сотрудника с минимальной зарплатой")
    void minSalary () {
        int departmentId = 1;
        when(employeeService.allEmployees()).thenReturn(employees);
        Employee expected = employees.stream()
                .filter(employee -> departmentId == employee.getDepartmentId())
                .min(Comparator.comparingInt(e -> e.getSalary()))
                .orElse(null);
        //test
        Employee actual = departmentService.minSalary(departmentId);
        // check
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    @DisplayName("Возвращает сумму зарплаты по отделу")
    void sumSalary () {
        int departmentId = 1;
        when(employeeService.allEmployees()).thenReturn(employees);
        Integer expected = employees.stream()
                .filter(employee -> departmentId == employee.getDepartmentId())
                .reduce(0,(partialSalaryResult, employee) -> partialSalaryResult + employee.getSalary(), Integer::sum);
        //test
        Integer actual = departmentService.sumSalary(departmentId);
        // check
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    @DisplayName("Возвращает список сотрудников по отделу")
    void allByDepty () {
        int departmentId = 1;
        when(employeeService.allEmployees()).thenReturn(employees);
        List<Employee> expected = employees.stream()
                .filter(employee -> departmentId == employee.getDepartmentId())
                .toList();

        //test
        List<Employee> actual = departmentService.allByDept(departmentId);
        // check
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    @DisplayName("Возвращает сотрудников, сгруппированых по отделам")
    void groupByDept () {
        when(employeeService.allEmployees()).thenReturn(employees);
        Map<Integer, List <Employee>> expected = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId));

        //test
        Map<Integer, List <Employee>> actual = departmentService.groupByDept();
        // check
        assertThat(expected).isEqualTo(actual);
    }



}
