package org.ms.securityservice.services;

import org.ms.securityservice.entities.*;
import org.ms.securityservice.repositories.AppUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    public UserDetailsServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        Collection<AppRole> appRoles = new ArrayList<>();
        appUser.getAppRoles().forEach(appRole ->{
            authorities.add(new SimpleGrantedAuthority(appRole.getRolename()));
        });
        return new User(appUser.getUsername(),appUser.getPassword(),authorities);
    }
}
