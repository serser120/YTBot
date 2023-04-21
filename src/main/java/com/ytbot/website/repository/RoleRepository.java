package com.ytbot.website.repository;

import com.ytbot.website.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends GenericRepository<Role> {
    Role findRoleByDescriptionContainingIgnoreCase(String description);
}
