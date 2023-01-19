package org.ms.securityservice.services;

import org.ms.securityservice.dto.AppRoleRequestDTO;
import org.ms.securityservice.dto.AppRoleResponseDTO;
import org.ms.securityservice.dto.AppUserRequestDTO;
import org.ms.securityservice.dto.AppUserResponseDTO;

import java.util.List;

public interface AccountService {
    AppUserResponseDTO addNewUser(AppUserRequestDTO appUserRequestDTO);
    AppRoleResponseDTO addNewRole(AppRoleRequestDTO appRoleRequestDTO);
    void addRoleToUser(String username, String rolename);
    AppUserResponseDTO loadUserByUsername(String username);
    List<AppUserResponseDTO> listUsers();
    List<AppRoleResponseDTO> listRoles();
}
