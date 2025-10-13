import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.Employee;


@RequestMapping("/emp")
@RestController
public class EmployeeController {


    @Autowired
    private EmployeeService empService;


    @PostMapping("/add")
    public Employee createEmp(@RequestBody Employee e){
        Employee e12=empService.createEmp(e);
        return e12;
    }

    @GetMapping("/{id}")
    public Employee getEmployeById(@PathVariable long id){
        Employee emp=empService.getById(id);
        if(emp==null){
            throw new RuntimeException("The Id was not present");
        }
        return emp;
    }
    
    @GetMapping("/getAll")
    public List<Employee> getAllemp(){
        Employee e=empService.getAllEmployees();
        return e;
    }

    @PutMapping("/update")

    public Employee getempUpdate(@PathVariable long id,@RequestBody Employee e12){
        return empService.UpdateEmployee(id,e12);
    }



}
