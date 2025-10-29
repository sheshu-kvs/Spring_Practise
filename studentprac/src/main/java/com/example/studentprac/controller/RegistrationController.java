package com.example.studentprac.controller;



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

import com.example.studentprac.model.student;
import com.example.studentprac.service.studentservise;

@RestController
public class RegistrationController {
    
    @Autowired 
    public studentservise serviceclas;
    
    @PostMapping("/upload")
    public String addOnedata(@RequestParam("name") String name,
                             @RequestParam("qualification") String qualification,
                             @RequestParam("email") String email,
                             @RequestParam("password") String password,
                             @RequestParam("address") String address,
                             @RequestParam("file") MultipartFile file) throws IOException{
                                     String uploadDir = "studentprac/uploads/";

                                     Files.createDirectories(Paths.get(uploadDir));

                                    String filename = file.getOriginalFilename();
                                    Path filepath = Paths.get(uploadDir, filename);
                                    Files.copy(file.getInputStream(),filepath,StandardCopyOption.REPLACE_EXISTING);

           student stu = new student();

           stu.setName(name);
           stu.setQualification(qualification);
           stu.setEmail(email);
           stu.setPassword(password);
           stu.setAddress(address);
           stu.setFilepath(filepath.toString());


           serviceclas.savestudent(stu);

        return "Student Saved Succesfully";
    }    

}
