package pro.sky.Employee.Book.and.Test;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface DepartmentService {
    Employee maxSalary (int departmentId);
    Employee minSalary (int departmentId);
    List<Employee> allByDept(int departmentId);
    Map<Integer, List<Employee>> groupByDept ();

}
