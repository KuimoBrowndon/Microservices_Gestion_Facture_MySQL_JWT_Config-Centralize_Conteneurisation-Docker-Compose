package org.ms.securityservice;

import org.ms.securityservice.dto.AppRoleRequestDTO;
import org.ms.securityservice.dto.AppUserRequestDTO;
import org.ms.securityservice.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication
@EnableEurekaClient
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityServiceApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner start(AccountService accountService){
        return args -> {
            accountService.addNewRole(new AppRoleRequestDTO("CUSTOMER_MANAGER"));
            accountService.addNewRole(new AppRoleRequestDTO("PRODUCT_MANAGER"));
            accountService.addNewRole(new AppRoleRequestDTO("BILL_MANAGER"));
            accountService.addNewRole(new AppRoleRequestDTO("ADMIN"));

            Stream.of("admin","user1","user2","user3","user4").forEach(
                us-> accountService.addNewUser(new AppUserRequestDTO(us,"1234","1234")
            ));

            accountService.addRoleToUser("user1","PRODUCT_MANAGER");
            accountService.addRoleToUser("user2","CUSTOMER_MANAGER");
            accountService.addRoleToUser("user3","PRODUCT_MANAGER");
            accountService.addRoleToUser("user4","BILL_MANAGER");
            accountService.addRoleToUser("admin","ADMIN");
            accountService.addRoleToUser("admin","PRODUCT_MANAGER");
        };
    }

}
