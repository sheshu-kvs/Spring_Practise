package com.sheshu.springjdbc1245;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepo {

    private final JdbcTemplate jdbctemplate;

    public EmployeeRepo(JdbcTemplate jdbctemplate) {
        this.jdbctemplate = jdbctemplate;
    }

    // it is used to convert the resultset to the java object
    private static final class EmpMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee emp = new Employee();
            emp.setId(rs.getLong("id"));
            emp.setName(rs.getString("name"));
            emp.setSalary(rs.getFloat("salary"));
            return emp;
        }
    }

    public List<Employee> findall() {
        return jdbctemplate.query("select * from emp", new EmpMapper());
    }

    public Employee findById(Long id) {
        return jdbctemplate.queryForObject("select * from emp where id = ?", new EmpMapper(), id);
    }

    public void deleteById(Long id) {
        jdbctemplate.update("delete from emp where id=?", id);
    }

    public Employee updateEmp(Employee emp) {
        jdbctemplate.update("update emp set name=?, salary=? where id=?",
        emp.getName(), emp.getSalary(), emp.getId());
        return emp;
    }
}
