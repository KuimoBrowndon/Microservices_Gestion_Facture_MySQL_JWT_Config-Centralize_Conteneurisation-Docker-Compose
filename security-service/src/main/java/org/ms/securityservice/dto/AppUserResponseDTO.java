package org.ms.securityservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ms.securityservice.entities.AppRole;

import java.util.ArrayList;
import java.util.Collection;

@Data @NoArgsConstructor @AllArgsConstructor
public class AppUserResponseDTO {
    private String id;
    private String username;
    private Collection<AppRole> appRoles = new ArrayList<>();
}
