package org.ms.securityservice.mappers;

import javax.annotation.Generated;
import org.ms.securityservice.dto.AppRoleRequestDTO;
import org.ms.securityservice.dto.AppRoleResponseDTO;
import org.ms.securityservice.entities.AppRole;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-11T17:04:18+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_331 (Oracle Corporation)"
)
@Component
public class AppRoleMapperImpl implements AppRoleMapper {

    @Override
    public AppRole AppRoleRequestTOAppRole(AppRoleRequestDTO appRoleRequestDTO) {
        if ( appRoleRequestDTO == null ) {
            return null;
        }

        AppRole appRole = new AppRole();

        appRole.setRolename( appRoleRequestDTO.getRolename() );

        return appRole;
    }

    @Override
    public AppRoleResponseDTO AppRoleToAppRoleResponseDTO(AppRole appRole) {
        if ( appRole == null ) {
            return null;
        }

        AppRoleResponseDTO appRoleResponseDTO = new AppRoleResponseDTO();

        appRoleResponseDTO.setId( appRole.getId() );
        appRoleResponseDTO.setRolename( appRole.getRolename() );

        return appRoleResponseDTO;
    }
}
