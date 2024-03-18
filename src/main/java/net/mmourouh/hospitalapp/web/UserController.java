package net.mmourouh.hospitalapp.web;

import lombok.AllArgsConstructor;
import net.mmourouh.hospitalapp.entities.User;
import net.mmourouh.hospitalapp.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> users() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{username}")
    public User user(@PathVariable String username){
        return userRepository.findByUsername(username);
    }
}
