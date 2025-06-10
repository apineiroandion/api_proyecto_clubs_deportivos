package com.dam_proyect.api_figth.mapper;

import com.dam_proyect.api_figth.dto.ClubCreateRequestDto;
import com.dam_proyect.api_figth.dto.ClubResponseDto;
import com.dam_proyect.api_figth.model.Club;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClubMapper {
    ClubMapper INSTANCE = Mappers.getMapper(ClubMapper.class);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "location", source = "location")
    @Mapping(target = "contactEmail", source = "contactEmail")
    @Mapping(target = "contactPhone", source = "contactPhone")
    @Mapping(target = "imageUrl", source = "imageUrl")

    Club toEntity(ClubCreateRequestDto dto);

    @Mapping(target = "createdAt", source = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    ClubResponseDto toResponseDto(Club entity);
}
