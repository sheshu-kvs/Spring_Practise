package com.demo.loginvalidation.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.demo.loginvalidation.model.Users;

@Repository
public class UsersRepo {

    private JdbcTemplate jdbctemplate;

    public UsersRepo(JdbcTemplate jdbctemplate) {
        this.jdbctemplate = jdbctemplate;
    }
    

    // sql will return the set of the data with the rows and the columns we need to create then
    //this RowMapper will convert the rs java object... 

    private static final class usersMapper implements RowMapper<Users> {
        @Override
        public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
            Users u = new Users();
            u.setId(rs.getLong("id"));
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            return u;
        }
    }


    public Users findByUserName(String username) {
        return jdbctemplate.queryForObject(
                "SELECT id, username, password FROM users WHERE username = ?",
                new usersMapper(),
                username);
    }
    public Users getUserById(Long id){
        try{
            return jdbctemplate.queryForObject("select * from users where id = ?", new usersMapper(),id);
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    // public Users updateUserById(Long id){
    //     return jdbctemplate.update("update users set username=? ,password=? where id=? ", new usersMapper(),
    //     id.getUsername(),id.getPassword());
    // }

    public void  deletebyUserId(Long id) {
      
             jdbctemplate.update("delete from users where id= ?", id);
       
    }

    public Users save(Users users) {
        jdbctemplate.update("insert into users (username,password) values(?,?)", users.getUsername(), users.getPassword());
        return users;
    }


    public List<Users> getAllUsers(){
        return jdbctemplate.query("select * from users", new usersMapper());
    }
}
