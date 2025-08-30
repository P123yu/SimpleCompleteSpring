package com.simple.Simple.service.impl;

import com.simple.Simple.co.UserCO;
import com.simple.Simple.dto.UserDTO;
import com.simple.Simple.exception.UserNotFound;
import com.simple.Simple.mapper.UserMapper;
import com.simple.Simple.model.User;
import com.simple.Simple.repository.UserRepository;
import com.simple.Simple.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO createUser(UserCO userCO) {
        User user=userMapper.coToEntity(userCO);
        user=userRepository.save(user);
        return userMapper.entityToDTO(user);
    }

    @Override
    public UserDTO readUser(Long id) {
        User user=userRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("no any user found"));
        return userMapper.entityToDTO(user);
    }

    @Override
    public List<UserDTO> readAllUsers() {
        List<User> userList= userRepository.findAll();
        return userMapper.entityListToDTOList(userList);
    }

    @Override
    public List<UserDTO> createAllUsers(List<UserCO> userCOList) {
        List<User> userList= userMapper.coListToEntityList(userCOList);
        userList=userRepository.saveAll(userList);
        return userMapper.entityListToDTOList(userList);
    }

    @Override
    public UserDTO updateUser(UserCO userCO) {
        Long id= Optional.ofNullable(userCO).map(UserCO::getId)
                .orElseThrow(()->new UserNotFound("id is missing"));
        boolean isUserExists=userRepository.existsById(id);
        if(!isUserExists){
           throw new NoSuchElementException("No any user exists with that id "+id);
        }
        User user=userMapper.coToEntity(userCO);
        user = userRepository.save(user);
        return userMapper.entityToDTO(user);
    }


//    @Override
//    public void deleteUserById(Long id) {
//         boolean isUserExists=userRepository.existsById(id);
//         if(isUserExists){
//             userRepository.deleteById(id);
//         }
//         else{
//             throw new NoSuchElementException("No any user exists with that id "+id);
//         }
//    }

    @Override
    public void deleteUserById(Long id) {
        boolean isUserExists=userRepository.existsById(id);
        if(!isUserExists) {
            throw new NoSuchElementException("No any user exists with that id " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO readUserByCity(String city) {
        User user= userRepository.findByCityIgnoreCase(city)
                .orElseThrow(()->new NoSuchElementException("No any city found"));
        return userMapper.entityToDTO(user);
    }

    @Override
    public void deleteUserByCity(String city) {
        boolean isCityExists=userRepository.existsByCityIgnoreCase(city);
        if(!isCityExists){
            throw new NoSuchElementException(city + " city not exists");
        }
       userRepository.deleteByCityIgnoreCase(city);
    }


}
