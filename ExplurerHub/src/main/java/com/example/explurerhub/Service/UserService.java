package com.example.explurerhub.Service;

import com.example.explurerhub.Model.User;

public interface UserService {

    void saveUser(User user);
    User findUserByUsername(String username);
    User findUserByID(Long id);
    Long getUserIdByUsername(String username);
}
