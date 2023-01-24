package com.spaceyatech.berlin.repository;

import com.spaceyatech.berlin.enums.RoleName;
import com.spaceyatech.berlin.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);


}
