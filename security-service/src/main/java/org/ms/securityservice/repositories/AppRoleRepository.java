package org.ms.securityservice.repositories;

import org.ms.securityservice.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {
    AppRole findByRolename(String rolename);
}
