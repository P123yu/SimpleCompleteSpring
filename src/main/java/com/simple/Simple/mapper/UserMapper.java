package com.simple.Simple.mapper;

import com.simple.Simple.co.UserCO;
import com.simple.Simple.dto.UserDTO;
import com.simple.Simple.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User coToEntity(UserCO userCO);

    UserDTO entityToDTO(User user);
}
