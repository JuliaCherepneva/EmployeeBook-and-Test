package pro.sky.Employee.Book.and.Test;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final int MAX_EMPLOYEES = 15;
    private final List<Employee> employees = new ArrayList<>(List.of(
            new Employee("Petr", "Petrov", 26754, 1),
            new Employee("Dima", " Dmitriev", 34434, 1),
            new Employee("Maxim", "Maximov", 87543, 2),
            new Employee("Nadya", "Ivanova", 30_120, 2),
            new Employee("Luda", "Lidovskya", 25_430, 3),
            new Employee("Alex", "Alexeev", 28_540, 3),
            new Employee("Vanya", "Vanovich", 24_240, 4),
            new Employee("Luba", "Super", 20_565, 4),
            new Employee("Anna", "Marchyk", 18_110, 5),
            new Employee("Dima", "Clayd", 17_590, 5)));

    public String hello () {
        return "Добро пожаловать в программу!";
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int salary, int departmentId) {
        if (!validateInput(firstName,lastName)){
            throw new InvalidInputException();
        }

        Employee employee = new Employee(firstName, lastName,salary,departmentId);
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        } else if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public String removeEmployee(String firstName, String lastName) {
        if (!validateInput(firstName,lastName)){
            throw new InvalidInputException();
        }

        boolean removed = employees.removeIf(p->p.getFirstName().equals(firstName) && p.getLastName().equals(lastName));
        if (removed){
            return "Сотрудник " + firstName + " " + lastName + " удален.";
        }
        return "Сотрудник " +  firstName + " " + lastName +  " не найден!";
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        if (!validateInput(firstName,lastName)){
            throw new InvalidInputException();
        }

        return employees.stream()
                .filter(e -> e.getFirstName().equals(firstName) && e.getLastName().equals(lastName))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException());
    }

    @Override
    public List <Employee> allEmployees() {
        return  employees;
    }

    private boolean validateInput(String firstName, String lastName) {
        return isAlpha(firstName) && isAlpha(lastName);
}

}

