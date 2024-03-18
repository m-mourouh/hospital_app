package net.mmourouh.hospitalapp.repositories;

import net.mmourouh.hospitalapp.entities.Role;
import net.mmourouh.hospitalapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByRoleName(String roleName);
}
