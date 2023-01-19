package org.ms.customerservice.security;

import org.ms.customerservice.configurations.ApplicationPropertiesConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final ApplicationPropertiesConfiguration applicationPropertiesConfiguration;

    public SecurityConfig(ApplicationPropertiesConfiguration applicationPropertiesConfiguration) {
        this.applicationPropertiesConfiguration = applicationPropertiesConfiguration;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/**").hasAnyAuthority("ADMIN","CUSTOMER_MANAGER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/**").hasAnyAuthority("ADMIN","CUSTOMER_MANAGER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/**").hasAnyAuthority("ADMIN","CUSTOMER_MANAGER");
        http.authorizeRequests().antMatchers("/h2-console/**","/swagger-ui/**","/v3/api-docs/**","/actuator/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilterBefore(new JWTAuthorizationFilter(applicationPropertiesConfiguration), UsernamePasswordAuthenticationFilter.class);
    }
}
