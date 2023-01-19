package org.ms.securityservice.services;

import org.ms.securityservice.dto.*;
import org.ms.securityservice.entities.*;
import org.ms.securityservice.exceptions.AuthNotFoundException;
import org.ms.securityservice.mappers.*;
import org.ms.securityservice.repositories.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AppUserMapper appUserMapper;
    private final AppRoleMapper appRoleMapper;

    public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, PasswordEncoder passwordEncoder, AppUserMapper appUserMapper, AppRoleMapper appRoleMapper) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.appUserMapper = appUserMapper;
        this.appRoleMapper = appRoleMapper;
    }

    @Override
    public AppUserResponseDTO addNewUser(AppUserRequestDTO appUserRequestDTO) {
        if(appUserRequestDTO.getUsername().equals("") ||
            appUserRequestDTO.getPassword().equals("") ||
            appUserRequestDTO.getConfirmedPassword().equals(""))
            throw new AuthNotFoundException("Données requises non reçues");
        if (!appUserRequestDTO.getPassword().equals(appUserRequestDTO.getConfirmedPassword()))
            throw new AuthNotFoundException("Les mots de passe ne correspondent pas.");
        if(appUserRepository.findByUsername(appUserRequestDTO.getUsername())!=null)
            throw new AuthNotFoundException("Utilisateur existant");
        AppUser appUser = appUserMapper.AppUserRequestDTOToAppUser(appUserRequestDTO);
        appUser.setId(UUID.randomUUID().toString());
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserMapper.AppUserToAppUserResponseDTO(appUserRepository.save(appUser));
    }

    @Override
    public AppRoleResponseDTO addNewRole(AppRoleRequestDTO appRoleRequestDTO) {
        if (appRoleRequestDTO.getRolename().equals(""))
            throw new AuthNotFoundException("Aucune donnée reçue");
        if(appRoleRepository.findByRolename(appRoleRequestDTO.getRolename())!=null)
            throw new AuthNotFoundException("Role existant");
        AppRole appRole = appRoleMapper.AppRoleRequestTOAppRole(appRoleRequestDTO);
        appRole.setId(UUID.randomUUID().toString());
        return appRoleMapper.AppRoleToAppRoleResponseDTO(appRoleRepository.save(appRole));
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        if (username.equals("") || rolename.equals(""))
            throw new AuthNotFoundException("Données requises non reçues");
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser == null)
            throw new AuthNotFoundException("Utilisateur inconnu");
        AppRole appRole = appRoleRepository.findByRolename(rolename);
        if(appRole == null)
            throw new AuthNotFoundException("Rôle inconnu");
        appUser.getAppRoles().add(appRole);
    }

    @Override
    public AppUserResponseDTO loadUserByUsername(String username) {
        return appUserMapper.AppUserToAppUserResponseDTO(appUserRepository.findByUsername(username));
    }

    @Override
    public List<AppUserResponseDTO> listUsers() {
        List<AppUser> appUsers = appUserRepository.findAll();
        return appUsers.stream()
                .map(appUserMapper::AppUserToAppUserResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppRoleResponseDTO> listRoles() {
        List<AppRole> appRoles = appRoleRepository.findAll();
        return appRoles.stream()
                .map(appRoleMapper::AppRoleToAppRoleResponseDTO)
                .collect(Collectors.toList());
    }
}
