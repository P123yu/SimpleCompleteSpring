package com.simple.Simple.controller;

import com.simple.Simple.co.UserCO;
import com.simple.Simple.dto.UserDTO;
import com.simple.Simple.service.UserService;
import com.simple.Simple.util.Constant;
import com.simple.Simple.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(Constant.CREATE_API)
    public ResponseEntity<ResponseUtil<UserDTO>>createUser(@Valid @RequestBody UserCO userCO){
        UserDTO userDTO=userService.createUser(userCO);
        ResponseUtil<UserDTO>response=ResponseUtil.<UserDTO>builder()
                .status(HttpStatus.CREATED.value())
                .message(Constant.CREATE)
                .data(userDTO).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping(Constant.READ_API+"/{id}")
    public ResponseEntity<ResponseUtil<UserDTO>>readUser(@PathVariable ("id") Long userId){
        UserDTO userDTO=userService.readUser(userId);
        ResponseUtil<UserDTO>response=ResponseUtil.<UserDTO>builder()
                .status(HttpStatus.OK.value())
                .message(Constant.READ)
                .data(userDTO).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping(Constant.READ_API)
    public ResponseEntity<ResponseUtil<List<UserDTO>>>readAllUsers(){
        List<UserDTO> userDTOList=userService.readAllUsers();
        ResponseUtil<List<UserDTO>>response=ResponseUtil.<List<UserDTO>>builder()
                .status(HttpStatus.OK.value())
                .message(Constant.READ)
                .data(userDTOList).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}
