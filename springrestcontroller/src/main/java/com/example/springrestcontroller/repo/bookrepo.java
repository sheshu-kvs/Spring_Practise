package com.example.springrestcontroller.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.springrestcontroller.model.book;

@Repository
public class bookrepo {
    private JdbcTemplate jdbctemplat;

    public bookrepo(JdbcTemplate jdbctemplat){
        this.jdbctemplat=jdbctemplat;
    }




    public static  final class bookmaper implements  RowMapper<book>{
    
       
        public book mapRow(ResultSet rs, int rownumber) throws SQLException{
            // creating the book object for the each row...
            book b = new book();
            b.setId(rs.getInt("id"));
            b.setName(rs.getString("name"));
            b.setAuthor(rs.getString("author"));
            b.setPrice(rs.getFloat("price"));
             return b; 
        }
    }




    //insert one book object


    public book insertOnebookObject(book b){
        String sql="insert into book(id,name,author,price) values (?,?,?,?)";
        jdbctemplat.update(sql,
        b.getId(),b.getName(),b.getAuthor(),b.getPrice());

        return b;
    }

    public List<book> getAllbooks(){
        String sql="select * from book";
        return jdbctemplat.query(sql,new bookmaper());
    }

    public book getOnebook(int id){
        String sql="select * from book where id= ?";
        try {
           return jdbctemplat.queryForObject(sql,new bookmaper(),id);
        } catch (EmptyResultDataAccessException e) {
            // if the no data was Found Means we will get the null
            // so we can display like this...
            return null;
        }
    }


    public book updateonebook(book b){
        String sql="update book set name=?,author=?,price=? where id = ?";
       
        jdbctemplat.update(sql,
        b.getName(),
        b.getAuthor(),
        b.getPrice(),b.getId()); 
        
        return getOnebook(b.getId());
    }

    public boolean deleteonebook(int id){
        String sql="delete from book where id=?";
       int  deletedrows = jdbctemplat.update(sql, id);
       if(deletedrows>0){
        return true;
       }
       return false;

    }
}
