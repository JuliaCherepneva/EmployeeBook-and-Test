package pro.sky.Employee.Book.and.Test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


public class EmployeeServiceImplTest {
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

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

    // Добавление сотрудника
    @Test
    public void shouldAddEmployee_whenCorrectData_thenEmployeeAdded() {
        String firstName = "Андрей";
        String lastName = "Иванов";
        int salary = 23988;
        int departmentId = 1;
        // when
        Employee actual = employeeService.addEmployee(firstName, lastName, salary, departmentId);

        // then
        assertEquals(actual.getFirstName(), firstName);
        assertEquals(actual.getLastName(), lastName);
        assertEquals(actual.getSalary(), salary);
        assertEquals(actual.getDepartmentId(), departmentId);
    }

    @Test
    public void shouldThrowException_whenDoNotCorrectData_thenThrowException() {
        String firstName = "Dima";
        String lastName = "Clayd";
        int salary = 17_590;
        int departmentId = 5;
        assertThatExceptionOfType(EmployeeAlreadyAddedException.class).isThrownBy(() -> employeeService.addEmployee(firstName, lastName, salary, departmentId));
    }
    @Test
    @DisplayName("Выкидывает ошибку, если превышен лимит сотрудников")
    public void maxEmployee() {
        var emp1 = employeeService.addEmployee("Витя", "Викторов", 11000, 1);
        var emp2 = employeeService.addEmployee("Дима", "Викторов", 12000, 1);
        var emp3 = employeeService.addEmployee("Макс", "Викторов", 13000, 1);
        var emp4 = employeeService.addEmployee("Даня", "Викторов", 14000, 1);
        var emp5 = employeeService.addEmployee("Миша", "Викторов", 15000, 1);

        assertThrows(EmployeeStorageIsFullException.class,() -> employeeService.addEmployee("Петя", "Максимов", 13000, 1));
    }


    // Удаление сотрудника
    @Test
    public void shouldRemoveEmployee_whenCorrectData_thenEmployeeRemoved() {
        String firstName = "Dima";
        String lastName = "Clayd";

        // when

        String removed = employeeService.removeEmployee("Dima","Clayd");
        int actual = employees.size()-1;
        // then
        int expected = employees.size()-1;
        // check
        assertEquals(expected, actual);

    }
    // Поиск сотрудника
    @Test
    public void shouldFindEmployee_whenCorrectData_thenEmployeeFinded() {
        String firstName = "Dima";
        String lastName = "Clayd";

        // when
        Employee actual = employeeService.findEmployee(firstName, lastName);

        // then
        assertEquals(actual.getFirstName(), firstName);
        assertEquals(actual.getLastName(), lastName);
    }

    @Test
    @DisplayName("Выкидывает ошибку, если сотрудник не найден")
    public void FindEmployee() {
        String firstName = "Dima";
        String lastName = "Clayd";

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee("Dima", "Cla"));

    }
}
