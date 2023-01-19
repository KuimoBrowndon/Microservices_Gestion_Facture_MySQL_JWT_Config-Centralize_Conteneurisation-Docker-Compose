package org.ms.securityservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ms.securityservice.configurations.ApplicationPropertiesConfiguration;
import org.ms.securityservice.entities.AppUser;
import org.ms.securityservice.exceptions.AuthNotFoundException;
import org.ms.securityservice.repositories.AppUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final AppUserRepository appUserRepository;
    private final ApplicationPropertiesConfiguration applicationPropertiesConfiguration;
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AppUserRepository appUserRepository, ApplicationPropertiesConfiguration applicationPropertiesConfiguration) {
        this.authenticationManager = authenticationManager;
        this.appUserRepository = appUserRepository;
        this.applicationPropertiesConfiguration = applicationPropertiesConfiguration;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser == null) {
            throw new AuthNotFoundException("Utilisateur inconnu");
        }
        else{
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username,password);
            return authenticationManager.authenticate(authenticationToken);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        User user = (User) authResult.getPrincipal();
        Algorithm algo1 = Algorithm.HMAC256(applicationPropertiesConfiguration.getSECRET());
        String jwtAccessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ applicationPropertiesConfiguration.getEXPIRE_ACCESS_TOKEN()))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algo1);
        String jwtRefreshAccessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ applicationPropertiesConfiguration.getEXPIRE_ACCESS_REFRESH_TOKEN()))
                .withIssuer(request.getRequestURL().toString())
                .sign(algo1);
        Map<String,String> idToken = new HashMap<>();
        idToken.put("access-token",jwtAccessToken);
        idToken.put("refresh-token",jwtRefreshAccessToken);
        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream(),idToken);
    }
}
