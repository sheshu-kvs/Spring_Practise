package com.example.studentmanagement.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.studentmanagement.model.student;
import com.example.studentmanagement.service.studentservice;


@RestController
public class RegistrationController {

    @Autowired
    public studentservice service;
    /*Spring reads the multipart/form-data request and gives you the uploaded file as an object.

You can call:

file.getOriginalFilename() → to get the name

file.getBytes() → to read content

file.transferTo(new File("path")) → to save it on disk */
   

    @PostMapping("/upload")
    public String RegisterStudent(@RequestParam("name") String name,
                                  @RequestParam("qualification") String qualification,
                                  @RequestParam("email") String email,
                                  @RequestParam("password") String password,
                                  @RequestParam("address") String address,
                                  @RequestParam("file") MultipartFile file) throws IOException{


                                    // Saving the File to the Disk..
                                                                                  
                                     String uploadDir = "uploads/";

                                     Files.createDirectories(Paths.get(uploadDir));

                                    String filename = file.getOriginalFilename();
                                    Path filepath = Paths.get(uploadDir, filename);
                                    Files.copy(file.getInputStream(),filepath,StandardCopyOption.REPLACE_EXISTING);



                                    // creating the student object 

                                    student stuObj = new student();

                                    stuObj.setName(name);
                                    stuObj.setQualification(qualification);
                                    stuObj.setEmail(email);
                                    stuObj.setAddress(address);
                                    stuObj.setPassword(password);
                                    stuObj.setFilepath(filepath.toString());

                                        
                                    service.save(stuObj);
                                    return "Student saved Successfully";

    } 
}
