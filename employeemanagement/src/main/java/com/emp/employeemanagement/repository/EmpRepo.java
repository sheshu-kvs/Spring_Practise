package com.emp.employeemanagement.repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.emp.employeemanagement.model.Emp;


@Repository
public class EmpRepo{

    private JdbcTemplate jdbctemplate;

    public EmpRepo(JdbcTemplate jdbcTemplate){
        this.jdbctemplate=jdbcTemplate;
    }



    public class EmpMapper implements RowMapper<Emp>{
        @Override
        public Emp mapRow(ResultSet rs,int rowNum) throws SQLException{
            Emp e=new Emp();
            e.setId(rs.getLong("id"));
            e.setName(rs.getString("name"));
            e.setSalary(rs.getFloat("salary"));
            return e;
        }
    }


    public Emp AddEmployee(Emp emp){
        jdbctemplate.update("insert into emp(id,name,salary) values(?,?,?)",emp.getId(),emp.getName()
        ,emp.getSalary());
        return emp;
    }
    
    public List<Emp> getAllEmp(){
        return jdbctemplate.query("select * from emp",new EmpMapper());
    }
    

    public Emp getEmpById(Long id){
      try{
            return jdbctemplate.queryForObject("select *  from emp where id=?",new EmpMapper(),id);
      }
      catch(EmptyResultDataAccessException e){
           return  null;
      }
    }


    public boolean DeleteEmpById(Long id){
            int affectedrows = jdbctemplate.update("delete from emp where id= ?" ,id);
            return affectedrows>0;
    }


    
    public Emp UpdateEmp(Emp emp){
         int updatedrows = jdbctemplate.update("update emp  set  name=?,salary=? where id=?",
         emp.getName(),emp.getSalary(), emp.getId());
         if(updatedrows==0){
            throw new RuntimeException("Emp Not Found with the id"+emp.getId());
         }
         return getEmpById(emp.getId());
    }
}






