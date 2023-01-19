package org.ms.securityservice.mappers;

import org.mapstruct.Mapper;
import org.ms.securityservice.dto.AppRoleRequestDTO;
import org.ms.securityservice.dto.AppRoleResponseDTO;
import org.ms.securityservice.entities.AppRole;

@Mapper(componentModel = "spring")
public interface AppRoleMapper {
    AppRole AppRoleRequestTOAppRole(AppRoleRequestDTO appRoleRequestDTO);
    AppRoleResponseDTO AppRoleToAppRoleResponseDTO(AppRole appRole);
}
