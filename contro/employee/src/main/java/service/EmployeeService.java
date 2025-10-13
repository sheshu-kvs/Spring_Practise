import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Employee;

@Service
public class EmployeeService {
   @Autowired //injects the Emprepo automatically..
    private EmployeeRepo empRepo;

    public Employee creatEmp(Employee e){
        return empRepo.save(e);
    }

    public Employee getEmpById(Employee e){
        return empRepo.findById();
    }
    public List<Employee> getAllEmployees(){
        return empRepo.findAll();
    }

    public Employee UpdateEmployee(long id,Employee empDetails){
        Employee ExitingEmp=empRepo.findById(id);
          ExitingEmp.setName(empDetails.getName());
          ExitingEmp.setRole(empDetails.getRole());
          ExitingEmp.setSalary(empDetails.getSalary());
          
          return empRepo.save(ExitingEmp);
    }
    public void deleteEmp(long id){
        empRepo.deleteById(id);
    }
}
