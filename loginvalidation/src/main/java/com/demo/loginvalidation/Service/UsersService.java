package com.demo.loginvalidation.Service;

import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.demo.loginvalidation.Repository.UsersRepo;
import com.demo.loginvalidation.model.Users;

@Service
public class UsersService {

    private final UsersRepo usersrepo;

    public UsersService(UsersRepo usersrepo) {
        this.usersrepo = usersrepo;
    }

    public boolean validateLogin(String username, String password) {
        try {
            Users user = usersrepo.findByUserName(username);
            if (user != null) {
                return password.equals(user.getPassword()); 
            }
            return false;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public Users saveUser(Users user) {
        return usersrepo.save(user);
    }

    public void deleteByUser(Long id) {
        usersrepo.deletebyUserId(id);
    }

    public List<Users> getallUsers(){
        return usersrepo.getAllUsers();
    }
    public Users getUsersById(Long id){
        return usersrepo.getUserById(id);
    }
}
