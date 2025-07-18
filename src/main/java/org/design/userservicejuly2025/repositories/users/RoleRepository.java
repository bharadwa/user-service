package org.design.userservicejuly2025.repositories.users;

import jdk.jfr.Registered;
import org.design.userservicejuly2025.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role save(Role entity);
}
