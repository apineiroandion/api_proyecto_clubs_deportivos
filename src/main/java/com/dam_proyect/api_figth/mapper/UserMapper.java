package com.dam_proyect.api_figth.mapper;

import com.dam_proyect.api_figth.dto.RegisterRequestDto;
import com.dam_proyect.api_figth.dto.UserDto;
import com.dam_proyect.api_figth.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterRequestDto dto);
    UserDto toDto(User user);
}
