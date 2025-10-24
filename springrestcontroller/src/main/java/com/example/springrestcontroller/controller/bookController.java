package com.example.springrestcontroller.controller;

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

import com.example.springrestcontroller.model.book;
import com.example.springrestcontroller.repo.bookrepo;

@RestController
/*When we  want deal RestFull Api's we need to use the RestController in the Spring 
 * like mapping like the get,post,put,delete in the spring...
 * we can use the restcontroller in the spring..
 * 
 * 
  */
@RequestMapping("/book")
public class bookController {




    private bookrepo bookrep;



    public bookController(bookrepo bookrep){
        this.bookrep = bookrep;
    }
    

    @GetMapping
    public List<book> geteAllBooks(){
        return bookrep.getAllbooks();
    }

    @PostMapping
    public book addAuthr(@RequestBody book b){
        return bookrep.insertOnebookObject(b);
    } 

    @PutMapping
    public ResponseEntity<?> updateEmp(@RequestBody book b){
        book book = bookrep.updateonebook(b);

        if(book == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entered Book was Not Found!!");
        }
        else{
            return ResponseEntity.status(HttpStatus.FOUND).body("Entered Book Was Succesfully Updated");
        }
    }

    // how many ways are there entered  emp is not found...

    @GetMapping("/{id}")

    public ResponseEntity<?> getonebook(@PathVariable int  id){
        book b = bookrep.getOnebook(id);
        if(b==null){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entered Id Was Not Found");
         }
       return ResponseEntity.status(HttpStatus.FOUND).body("Entered Book Was Found "+id);
       /*keep when you are  dealing with the frontend we need to use the instead FOUND we can use the 
        * .OK 
        */
    }


    @DeleteMapping
    public ResponseEntity<?> deleteOneBook(@PathVariable int id){
        boolean val = bookrep.deleteonebook(id);
        if(val){
            return ResponseEntity.status(HttpStatus.FOUND).body("Entered book Id Was Succesfully Deleted");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entered Book Id Was Not Found...");
        }

    }

}
