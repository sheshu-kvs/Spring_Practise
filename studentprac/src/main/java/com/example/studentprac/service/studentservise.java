package com.example.studentprac.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.studentprac.model.student;
import com.example.studentprac.repo.studentrepo;



@Service
public class studentservise implements UserDetailsService {


     @Autowired     
     private  studentrepo repo;

     @Autowired
     private PasswordEncoder passwordencoder;

     public student savestudent(student stu){
        stu.setPassword(passwordencoder.encode(stu.getPassword()));
        return repo.save(stu);
     }

// loadUserByUsername  this method will fetch the detils in the db if the user is present or not...

    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException{
        Optional<student> user = repo.findByName(name);

        if(user.isEmpty()){
            throw new UsernameNotFoundException(name);
        }


        var userObj = user.get();
        String role = userObj.getRole();

        if(role==null || role.isBlank()){
            role="USER";
        }

        return User.builder()
        .username(userObj.getName())
        .password(userObj.getPassword())
        .roles(role)
        .build();

    }


/*updateUser(User user)
→ updates profile fields.

getUserDetails(String username)
→ fetches user info from DB.

updateResume(MultipartFile file)
→ saves file and updates file name/path in DB. */

public student getStudent(String name){
      Optional<student> stu = repo.findByName(name);
      return stu.orElse(null);
}


public student updateStudent(student stu){
    Optional<student> existingstuOpt = repo.findById(stu.getId());

    if(existingstuOpt.isEmpty()){
        throw new ResourceNotFoundException("student is the Empty "+stu.getId());
    }
    student existingstu = existingstuOpt.get();
    existingstu.setName(stu.getName());
    existingstu.setAddress(stu.getAddress());
    existingstu.setEmail(stu.getEmail());
    existingstu.setFilepath(stu.getFilepath());
    return repo.save(existingstu);
}

public void updateResume(MultipartFile file,String name) throws IOException{
    if(file.isEmpty()){
        throw new IllegalStateException("Cannot Upload the Empty File");
        }
         String uploadDir = "studentprac/uploads/";
         Path storageDir = Paths.get(uploadDir);
         Files.createDirectories(storageDir);


         String filename = file.getOriginalFilename();
         Path filepath = storageDir.resolve(filename);

        Files.copy(file.getInputStream(),filepath,StandardCopyOption.REPLACE_EXISTING);

         Optional<student> stuOpt  = repo.findByName(name);

        if(stuOpt.isPresent()){
        student stu = stuOpt.get();
        stu.setFilepath(filepath.toString());
        repo.save(stu);
       }
       else{
        throw new ResourceNotFoundException("student Not Found....");
       }

}

public List<student> getAll(){
    return repo.findAll();
}

}
