package com.example.springwithjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.springwithjpa.model.product;
import com.example.springwithjpa.repo.productrepositroy;

@Service
public class productservice {

    @Autowired
    private productrepositroy productrepo;



    public productservice(productrepositroy productrepo){
        this.productrepo = productrepo;
    }

 
    // insert one product

    public product InsertOneProduct(product p){
       return  productrepo.save(p);
    }

    public List<product> getAllproduct(){
        return productrepo.findAll();
    }

    // to get the One product...

    public product getOneProduct(long id){
     try{
         return productrepo.findById(id).orElse(null);
     }
     catch(EmptyResultDataAccessException e){
         return null;   
         }
     }
     public boolean deleteOneProduct(long id){
       if(productrepo.existsById(id)){
        productrepo.deleteById(id);
        return true;
       }
       return false;
     }



     public boolean updateProduct(product updateProduct){


        if(productrepo.existsById(updateProduct.getId())){
              product existinprd = productrepo.findById(updateProduct.getId()).get();
              existinprd.setProductname(updateProduct.getProductname());
              existinprd.setPrice(updateProduct.getPrice());
              productrepo.save(existinprd);
             return true;

        }
        return false;
      
     }






}