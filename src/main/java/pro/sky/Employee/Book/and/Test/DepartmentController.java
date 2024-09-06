package pro.sky.Employee.Book.and.Test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    
    @GetMapping (path = "/max-salary")
        public Employee maxSalary (@RequestParam ("departmentId") Integer departmentId) {
        return departmentService.maxSalary (departmentId);

    }


    @GetMapping (path = "/min-salary")
        public Employee minSalary (@RequestParam ("departmentId") Integer departmentId) {

        return departmentService.minSalary (departmentId);

    }

    @GetMapping (path = "/all", params = "departmentId")
        public List <Employee> allByDept (@RequestParam ("departmentId") Integer departmentId) {
        return departmentService.allByDept(departmentId);

    }
    @GetMapping(path = "/all")
    public Map <Integer, List<Employee>> groupByDept (){
        return departmentService.groupByDept();
    }
}
