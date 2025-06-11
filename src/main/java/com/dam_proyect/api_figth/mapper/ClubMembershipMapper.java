package com.dam_proyect.api_figth.mapper;

import com.dam_proyect.api_figth.dto.ClubMembershipCreateRequestDto;
import com.dam_proyect.api_figth.dto.ClubMembershipResponseDto;
import com.dam_proyect.api_figth.dto.ClubResponseDto;
import com.dam_proyect.api_figth.dto.UserDto;
import com.dam_proyect.api_figth.model.Club;
import com.dam_proyect.api_figth.model.ClubMembership;
import com.dam_proyect.api_figth.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClubMembershipMapper {

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "clubId", target = "club")
    ClubMembership toEntity(ClubMembershipCreateRequestDto dto);

    @Mapping(source = "user", target = "user")
    @Mapping(source = "club", target = "club")
    ClubMembershipResponseDto toResponseDto(ClubMembership entity);

    List<ClubMembershipResponseDto> toResponseDtoList(List<ClubMembership> entities);

    UserDto userToUserResponseDto(User user);
    ClubResponseDto clubToClubResponseDto(Club club);

    default User mapUser(String id) {
        if (id == null) return null;
        User user = new User();
        user.setId(id);
        return user;
    }

    default Club mapClub(String id) {
        if (id == null) return null;
        Club club = new Club();
        club.setId(id);
        return club;
    }
}
