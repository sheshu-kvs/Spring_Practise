package com.emp.employeemanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.emp.employeemanagement.model.Emp;
import com.emp.employeemanagement.repository.EmpRepo;

@Service
public class EmpService {

    private final EmpRepo emprepo;

    public EmpService(EmpRepo emprepo){
        this.emprepo = emprepo;
    }


    public Emp AddEmpDetails(Emp emp){
        return emprepo.AddEmployee(emp);  
    }

    public List<Emp> getAllEmp(){
        return emprepo.getAllEmp();
    }

    public Emp getEmpByid(Long id){
        return emprepo.getEmpById(id);
    }

    public Emp UpdateEmp(Emp emp){
       return emprepo.UpdateEmp(emp);
    }

    public boolean DeleteEmpById(Long id){
         return emprepo.DeleteEmpById(id);
    }

}
