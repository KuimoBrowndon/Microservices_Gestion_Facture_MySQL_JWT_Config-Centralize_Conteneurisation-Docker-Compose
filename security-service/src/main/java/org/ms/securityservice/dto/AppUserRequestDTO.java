package org.ms.securityservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AppUserRequestDTO {
    private String username;
    private String password;
    private String confirmedPassword;
}
