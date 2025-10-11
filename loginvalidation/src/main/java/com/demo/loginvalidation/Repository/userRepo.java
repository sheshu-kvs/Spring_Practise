package com.demo.loginvalidation.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.demo.loginvalidation.model.user;

@Repository
public class userRepo {

    private JdbcTemplate jdbctemplate;

    public userRepo(JdbcTemplate jdbctemplate) {
        this.jdbctemplate = jdbctemplate;
    }

    private static final class userMapper implements RowMapper<user> {
        @Override
        public user mapRow(ResultSet rs, int rowNum) throws SQLException {
            user u = new user();
            u.setId(rs.getLong("id"));
            u.setName(rs.getString("name"));
            u.setEmail(rs.getString("email"));
            u.setPhonenumber(rs.getString("phonenumber"));
            return u;
        }
    }

    public  List<user> findall(){        
        return jdbctemplate.query("select * from users", new userMapper());
    }

    public user findById(Long id){
        return jdbctemplate.queryForObject("select * from user where id = ?", new userMapper(),id);
        
    }
    public void deleteById(Long id){
        jdbctemplate.update("delete from emp where id = ?",new userMapper(),id);
    }
    public user updatUser(user u){
         jdbctemplate.update("update emp set name=? ,email=?, phonenumber=? where id=? ",
        u.getName(),u.getEmail(),u.getPhonenumber());
        return u;
    } 

}


