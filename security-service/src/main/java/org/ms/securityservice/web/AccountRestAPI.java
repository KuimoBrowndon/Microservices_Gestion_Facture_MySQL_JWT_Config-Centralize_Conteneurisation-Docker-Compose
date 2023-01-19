package org.ms.securityservice.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.ms.securityservice.configurations.ApplicationPropertiesConfiguration;
import org.ms.securityservice.dto.*;
import org.ms.securityservice.entities.*;
import org.ms.securityservice.repositories.AppUserRepository;
import org.ms.securityservice.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api")
public class AccountRestAPI {
    private final AccountService accountService;
    private final AppUserRepository appUserRepository;
    private final ApplicationPropertiesConfiguration applicationPropertiesConfiguration;

    public AccountRestAPI(AccountService accountService, AppUserRepository appUserRepository, ApplicationPropertiesConfiguration applicationPropertiesConfiguration) {
        this.accountService = accountService;
        this.appUserRepository = appUserRepository;
        this.applicationPropertiesConfiguration = applicationPropertiesConfiguration;
    }

    @GetMapping(path = "/users")
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('CUSTOMER_MANAGER')")
    public List<AppUserResponseDTO> appUsers(){
        return accountService.listUsers();
    }

    @GetMapping(path = "/users/{username}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public AppUserResponseDTO loadAppUser(@PathVariable String username){
        return accountService.loadUserByUsername(username);
    }

    @PostMapping(path = "/users")
    @PostAuthorize("hasAuthority('ADMIN')")
    public AppUserResponseDTO saveUser(@RequestBody AppUserRequestDTO appUserRequestDTO){
        return accountService.addNewUser(appUserRequestDTO);
    }

    @GetMapping(path = "/roles")
    @PostAuthorize("hasAuthority('ADMIN')")
    public List<AppRoleResponseDTO> appRoles(){
        return accountService.listRoles();
    }

    @PostMapping(path = "/roles")
    @PostAuthorize("hasAuthority('ADMIN')")
    public AppRoleResponseDTO saveRole(@RequestBody AppRoleRequestDTO appRoleRequestDTO){
        return accountService.addNewRole(appRoleRequestDTO);
    }

    @PostMapping(path = "/addRoleToUser")
    @PostAuthorize("hasAuthority('ADMIN')")
    public void saveRoleUser(@RequestBody RoleUserForm roleUserForm){
        accountService.addRoleToUser(roleUserForm.getUsername(),roleUserForm.getRolename());
    }

    @GetMapping(path = "/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String authToken = request.getHeader(applicationPropertiesConfiguration.getAUTH_HEADER());
        if (authToken!=null && authToken.startsWith(applicationPropertiesConfiguration.getAUTH_HEADER_PREFIX())){
            String jwtRefreshAccessToken = authToken.substring(applicationPropertiesConfiguration.getAUTH_HEADER_PREFIX().length());
            Algorithm algorithm = Algorithm.HMAC256(applicationPropertiesConfiguration.getSECRET());
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(jwtRefreshAccessToken);
            String username = decodedJWT.getSubject();
            AppUser appUser = appUserRepository.findByUsername(username);
            String jwtAccessToken = JWT.create()
                    .withSubject(appUser.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis()+ applicationPropertiesConfiguration.getEXPIRE_ACCESS_TOKEN()))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles",appUser.getAppRoles().stream().map(AppRole::getRolename).collect(Collectors.toList()))
                    .sign(algorithm);
            Map<String,String> idToken = new HashMap<>();
            idToken.put("access-token",jwtAccessToken);
            idToken.put("refresh-token",jwtRefreshAccessToken);
            response.setContentType("application/json");
            new ObjectMapper().writeValue(response.getOutputStream(),idToken);
        }
        else{
            throw  new RuntimeException("RefreshToken Requis.");
        }
    }

    @GetMapping(path = "/profile")
    @PostAuthorize("hasAuthority('ADMIN')")
    public AppUserResponseDTO profile(Principal principal){
        return accountService.loadUserByUsername(principal.getName());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
@Data
class RoleUserForm{
    private String username;
    private String rolename;
}