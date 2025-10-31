package com.example.studentprac.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.studentprac.model.student;
import com.example.studentprac.service.studentservise;

@RestController
public class RestControllerClass {

    @Autowired
    public studentservise serviceclass;

    @GetMapping("/users/dashboard")
    public ResponseEntity<student> getDashboardDetails(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
         student stu = serviceclass.getStudent(username);
         return ResponseEntity.ok(stu);
    }

    @GetMapping("/admins/dashboard")
    public List<student> getAllAdminDashBoard(){
        return serviceclass.getAll();
    }

@GetMapping("/users/resume/{filename}")
public ResponseEntity<Resource> viewfile(@PathVariable String filename) throws IOException {
    Resource resource = serviceclass.getFileasResource(filename);

    return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_PDF)
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
            .body(resource);
}


//for the downloading we are the same as the above controller...

@GetMapping("/users/download/{filename}")
public ResponseEntity<Resource> downloadfile(@PathVariable String filename) throws IOException{
    Resource resource  = serviceclass.getFileasResource(filename);
    return ResponseEntity.ok()
    .contentType(MediaType.APPLICATION_PDF)
    .header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\""+ filename +"\"")
    .body(resource);

} 


// @GetMapping("/{id}")
// public student getStudentById(@PathVariable long id){
//     // Implement this method in your service class
//     return serviceclass.getStudentById(id);
// }


// /admins/dashboard/delete/${id
@DeleteMapping("/admins/dashboard/delete/{id}")
public void deletestudent(@PathVariable Long id){
    serviceclass.deletestudent(id);

}

// get the student detail before updating 

@GetMapping("/admins/dashboard/{id}") 
public student getStudentById(@PathVariable Long id) {
     return serviceclass.getStudentById(id);
     }

// update student with the resume

@PutMapping(value = "/admins/dashboard/{id}", consumes = {"multipart/form-data"})
public ResponseEntity<student> updateStudentWithResume(
        @PathVariable Long id,
        @RequestPart("student") student stu, // JSON with name, email, etc.
        @RequestPart(value = "file", required = false) MultipartFile file // Optional file
        // this is an optional we cand add the file or skip the file we given value = file required = false..
) throws IOException {

    // Step 1: Set ID (ensure consistency)
    stu.setId(id);

    // Step 2: Update student details
    student updatedStudent = serviceclass.updateStudent(stu);

    // Step 3: If file is provided, update the resume
    if (file != null && !file.isEmpty()) {
        // here, use the name or username field to locate the student in DB
        serviceclass.updateResume(file, stu.getName());
        // refresh the updated student after file update
        updatedStudent = serviceclass.getStudent(stu.getName());
    }

    // Step 4: Return final updated student
    return ResponseEntity.ok(updatedStudent);
}



   } 

    
    