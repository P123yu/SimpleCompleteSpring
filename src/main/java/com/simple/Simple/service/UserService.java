package com.simple.Simple.service;

import com.simple.Simple.co.UserCO;
import com.simple.Simple.dto.UserDTO;
import com.simple.Simple.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    // NORMAL CRUD ===========================================

    // create
    UserDTO createUser(UserCO userCO);

    // read by id
    UserDTO readUser(Long id);

    // read all
    List<UserDTO> readAllUsers();

    // create all
    List<UserDTO> createAllUsers(List<UserCO> userCOList);

    // update user
    UserDTO updateUser (UserCO userCO);

    // delete by id
    void deleteUserById(Long id);

    // CUSTOM CRUD =================================================

    // find by city
    UserDTO readUserByCity(String city);

    // delete by city
    void deleteUserByCity(String city);





}
