package org.ms.securityservice.mappers;

import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.Generated;
import org.ms.securityservice.dto.AppUserRequestDTO;
import org.ms.securityservice.dto.AppUserResponseDTO;
import org.ms.securityservice.entities.AppRole;
import org.ms.securityservice.entities.AppUser;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-11T17:04:19+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_331 (Oracle Corporation)"
)
@Component
public class AppUserMapperImpl implements AppUserMapper {

    @Override
    public AppUser AppUserRequestDTOToAppUser(AppUserRequestDTO appUserRequestDTO) {
        if ( appUserRequestDTO == null ) {
            return null;
        }

        AppUser appUser = new AppUser();

        appUser.setUsername( appUserRequestDTO.getUsername() );
        appUser.setPassword( appUserRequestDTO.getPassword() );

        return appUser;
    }

    @Override
    public AppUserResponseDTO AppUserToAppUserResponseDTO(AppUser appUser) {
        if ( appUser == null ) {
            return null;
        }

        AppUserResponseDTO appUserResponseDTO = new AppUserResponseDTO();

        appUserResponseDTO.setId( appUser.getId() );
        appUserResponseDTO.setUsername( appUser.getUsername() );
        Collection<AppRole> collection = appUser.getAppRoles();
        if ( collection != null ) {
            appUserResponseDTO.setAppRoles( new ArrayList<AppRole>( collection ) );
        }

        return appUserResponseDTO;
    }
}
