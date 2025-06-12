package com.dam_proyect.api_figth.mapper;

import com.dam_proyect.api_figth.dto.RegisterRequestDto;
import com.dam_proyect.api_figth.dto.UserDto;
import com.dam_proyect.api_figth.dto.UserUpdateRequestDto;
import com.dam_proyect.api_figth.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterRequestDto dto);
    UserDto toDto(User user);

    void  updateUserFromDto(UserUpdateRequestDto dto, @MappingTarget User user);

    default String map(java.util.Optional<String> value) {
        return value.orElse(null);
    }
}

