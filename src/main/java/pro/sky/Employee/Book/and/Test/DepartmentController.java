package pro.sky.Employee.Book.and.Test;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    
    @GetMapping (path = "/{id}/salary/max")
        public Employee maxSalary (@PathVariable("id") Integer departmentId) {
        return departmentService.maxSalary (departmentId);

    }

    @GetMapping (path = "/{id}/salary/min")
        public Employee minSalary (@PathVariable("id") Integer departmentId) {

        return departmentService.minSalary (departmentId);

    }

    @GetMapping (path = "/{id}/salary/sum")
    public Integer sumSalary (@PathVariable("id") Integer departmentId) {
        return departmentService.sumSalary (departmentId);

    }

    @GetMapping (path = "{id}/employees")
        public List <Employee> allByDept (@PathVariable("id") Integer departmentId) {
        return departmentService.allByDept(departmentId);

    }
    @GetMapping(path = "/employees")
    public Map <Integer, List<Employee>> groupByDept (){
        return departmentService.groupByDept();
    }
}
