package com.vendor.vendordetails.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendor.vendordetails.model.cloudVendor.cloudmodel;

@RestController
@RequestMapping("/cloudVendor")
public class cloudvendorcontroller {
     
    cloudmodel cm;



    @GetMapping()
    public cloudmodel getAllVendor(){
        return cm;
    }




    @GetMapping("{id}")    
    public cloudmodel  getVeendor(){
      return cm;
        
    }
    
    @PostMapping()
    public String AddingVedorDetails(@RequestBody cloudmodel cm){
        this.cm=cm;
        return"Vendor Details Added Successfully";
    }


    @PutMapping()
    public String UpdatedVendorDetails(@RequestBody cloudmodel cm){
        this.cm=cm;
        return"Vendor Updated   Successfully";
    }

    @DeleteMapping({"id"})
        public String DelteVendorDetails(@RequestBody cloudmodel cm){
        this.cm=cm;
        return"Vendor Deleted  Successfully";
    }


}
