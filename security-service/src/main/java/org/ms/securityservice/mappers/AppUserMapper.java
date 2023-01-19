package org.ms.securityservice.mappers;

import org.mapstruct.Mapper;
import org.ms.securityservice.dto.AppUserRequestDTO;
import org.ms.securityservice.dto.AppUserResponseDTO;
import org.ms.securityservice.entities.AppUser;

@Mapper(componentModel = "spring")
public interface AppUserMapper {
    AppUser AppUserRequestDTOToAppUser(AppUserRequestDTO appUserRequestDTO);
    AppUserResponseDTO AppUserToAppUserResponseDTO(AppUser appUser);
}
