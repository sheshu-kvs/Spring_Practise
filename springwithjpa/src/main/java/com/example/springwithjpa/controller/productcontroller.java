package com.example.springwithjpa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springwithjpa.model.product;
import com.example.springwithjpa.repo.productrepositroy;
import com.example.springwithjpa.service.productservice;

@RestController
@RequestMapping("/product")
public class productcontroller {

    /*~~(Unable to determine parameter type)~~>*/
    private productrepositroy productrepositroy;

    private productservice productservice;

    public productcontroller(productservice productservice){
        this.productservice=productservice;
    }

    @PostMapping
    public product addoneProduct(@RequestBody product p){
        return productservice.InsertOneProduct(p);
    }

    @GetMapping
    
    public List<product> getallProducts(){
        return productservice.getAllproduct();
    } 


    @GetMapping("/{id}")
    public ResponseEntity<?> getOneProduct(@PathVariable long id){
        product p= productservice.getOneProduct(id);
        if(p==null){
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entered Id Was Not Found...");
        }
        else{
            return ResponseEntity.status(HttpStatus.FOUND).body("Entered Id Was Found!!");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteonebyID(@PathVariable Long id ){
        boolean product = productservice.deleteOneProduct(id);
        if(product){
            return ResponseEntity.status(HttpStatus.FOUND).body("Entered Id Was Succesfully deleted");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enterd id Was Not Found");
        }
    }


    // to update the product we are using the putmapping

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody product product12){
        boolean val = productservice.updateProduct(product12);
        if(val){
            return ResponseEntity.status(HttpStatus.FOUND).body("Entered Prodcut was Successfully Updated");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enterd Product was Not Found");
        }
    }
    
}
