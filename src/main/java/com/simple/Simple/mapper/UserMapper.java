package com.simple.Simple.mapper;

import com.simple.Simple.co.UserCO;
import com.simple.Simple.dto.UserDTO;
import com.simple.Simple.model.User;
import com.simple.Simple.util.ByteArrayMapper;
import org.mapstruct.Mapper;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

//@Mapper(componentModel = "spring")

@Mapper(componentModel = "spring", uses = ByteArrayMapper.class)
public interface UserMapper {
    User coToEntity(UserCO userCO);

    UserDTO entityToDTO(User user);

    List<UserDTO> entityListToDTOList(List<User>userList);

    List<User> coListToEntityList(List<UserCO>userCOList);

}
