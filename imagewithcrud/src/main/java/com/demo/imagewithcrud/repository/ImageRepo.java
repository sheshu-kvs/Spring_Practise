package com.demo.imagewithcrud.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.demo.imagewithcrud.model.Image;

@Repository
public class ImageRepo {

    private JdbcTemplate jdbctemplate;

    public ImageRepo(JdbcTemplate jdbctemplate){
        this.jdbctemplate=jdbctemplate;
    }
    

    private class ImageMapper implements RowMapper<Image>{
        public Image mapRow(ResultSet rs, int rowNum) throws SQLException{
            Image i1=new Image();

            i1.setId(rs.getLong("id"));
            i1.setImagename(rs.getString("imagename"));
            i1.setImagetype(rs.getString("imagetype"));
            i1.setImagedata(rs.getBytes("imagedata"));
            return i1;
        }

    }



    // adding the Image in the DB
    public Image save(Image img){

        String sql="insert into image (imagename,imagetype,imagedata) values(?,?,?)";
        jdbctemplate.update(sql,
        img.getImagename(),
        img.getImagetype(),
        img.getImagedata());

        return img;
    }



     public List<Image> getAllImages() {
        String sql = "select * from image";
        return jdbctemplate.query(sql,new ImageMapper());
    }



        public Image getImageById(Long id){
            String sql="select * from image where id = ?";
            return jdbctemplate.queryForObject(sql,new ImageMapper(), id);
        }


        public Image updatImage(Image img){
            String sql="update image set imagename = ?, imagetype = ?,imagedata = ? where id = ?";
              int updatedrows = jdbctemplate.update(sql,
               img.getImagename(),
               img.getImagetype(),
               img.getImagedata(),
               img.getId());

               if(updatedrows == 0){
                throw new RuntimeException("Enterd Employee was Not Found"+img.getId());
               }
               else{
                return getImageById(img.getId());
               }
        }

        
        public boolean DeleteImageById(Long id){
            String sql="delete from image where id= ?";
             int affectedrows=jdbctemplate.update(sql, id);
             return affectedrows>0;

        }

}
