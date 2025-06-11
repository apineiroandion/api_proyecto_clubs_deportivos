package com.dam_proyect.api_figth.mapper;

import com.dam_proyect.api_figth.dto.ClubCreateRequestDto;
import com.dam_proyect.api_figth.dto.ClubResponseDto;
import com.dam_proyect.api_figth.model.Club;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClubMapper {

    Club toEntity(ClubCreateRequestDto dto);

    @Mapping(target = "createdAt", source = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    ClubResponseDto toResponseDto(Club entity);
}
