package org.ms.securityservice.security;

import org.ms.securityservice.configurations.ApplicationPropertiesConfiguration;
import org.ms.securityservice.repositories.AppUserRepository;
import org.ms.securityservice.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsService;
    private final AppUserRepository appUserRepository;
    private final ApplicationPropertiesConfiguration applicationPropertiesConfiguration;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, AppUserRepository appUserRepository, ApplicationPropertiesConfiguration applicationPropertiesConfiguration) {
        this.userDetailsService = userDetailsService;
        this.appUserRepository = appUserRepository;
        this.applicationPropertiesConfiguration = applicationPropertiesConfiguration;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/h2-console/**","/api/refreshToken/**","/login/**","/swagger-ui/**","/v3/api-docs/**","/actuator/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new JWTAuthenticationFilter(authenticationManagerBean(), appUserRepository, applicationPropertiesConfiguration));
        http.addFilterBefore(new JWTAuthorizationFilter(applicationPropertiesConfiguration), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
