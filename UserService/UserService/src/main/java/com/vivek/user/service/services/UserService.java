package com.vivek.user.service.services;

import com.vivek.user.service.entities.User;

import java.util.List;

public interface UserService {

    //create
    User saveUser(User user);

    //get all users
    List<User> getAllUser();

    //get single user of give userId
    User getUser(String userId);

    //ToDo delete
    //ToDo update

}
