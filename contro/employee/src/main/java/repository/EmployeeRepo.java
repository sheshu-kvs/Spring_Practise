import java.util.HashMap;

import com.example.model.Employee;

public class EmployeeRepo {
        
   HashMap<Long,Employee> hashmap=new HashMap<>();
   long incre=1;


   public Employee save(Employee e){

    hashmap.put(e.getId(),e);
    return e;
   }
   public Employee getById(long id){
    return hashmap.get(id);
   }

   public Map<Long,Employee> findAll(){
    return hashmap.values();        
   }

   public void deleteById(long id){
    hashmap.remove(id);
   }

}
