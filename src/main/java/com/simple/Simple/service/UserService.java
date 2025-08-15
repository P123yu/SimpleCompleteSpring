package com.simple.Simple.service;

import com.simple.Simple.co.UserCO;
import com.simple.Simple.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    // create
    UserDTO createUser(UserCO userCO);

    // read by id
    UserDTO readUser(Long id);

    // read all

//    List<User>

    // create all

    // delete by id


}
