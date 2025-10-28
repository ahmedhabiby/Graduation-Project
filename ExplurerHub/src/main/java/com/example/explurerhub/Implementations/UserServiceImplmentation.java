package com.example.explurerhub.Implementations;

import com.example.explurerhub.Model.User;
import com.example.explurerhub.Repository.UserRepo;
import com.example.explurerhub.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplmentation implements UserService {

    private UserRepo userRepo;
    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Override
    public void saveUser(User user) {
        userRepo.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        User user =userRepo.findByUsername(username);
        return user;
    }

    @Override
    public User findUserByID(Long id) {
        User user = userRepo.findById(id).orElseThrow();
        return user;
    }

    @Override
    public Long getUserIdByUsername(String username) {
        Long id=userRepo.findByUsername(username).getId();
        return id;
    }
}
