package net.mmourouh.hospitalapp.services;

import net.mmourouh.hospitalapp.entities.Role;
import net.mmourouh.hospitalapp.entities.User;

public interface UserService {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUsername(String username);
    Role findRoleByName(String name);
    void addRoleToUser(String username, String roleName);

    User authenticate(String username, String password);
}
