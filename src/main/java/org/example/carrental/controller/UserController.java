package org.example.carrental.controller;

import org.example.carrental.model.Role;
import org.example.carrental.model.User;
import org.example.carrental.repository.RoleRepository;
import org.example.carrental.repository.UserRepository;
import org.example.carrental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") @Param("id") Long id){
        return userRepository.findById(id).orElseThrow(null);//TODO)
    }

    @GetMapping("")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") @Param("id") Long id, @RequestBody User user){
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User with ID " + id + " not found.")
                );

        if (user.getRole() != null && (user.getRole().getId() != null || user.getRole().getRoleType() != null)) {
            Optional<Role> role = roleRepository.findById(user.getRole().getId());
            existingUser.setRole(role.get());
        }

        if (user.getName() != null) existingUser.setName(user.getName());
        if (user.getEmail() != null) existingUser.setEmail(user.getEmail());
        if (user.getPhone() != null) existingUser.setPhone(user.getPhone());
        if (user.getPassword() != null) existingUser.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(existingUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") @Param("id") Long id){
        userRepository.deleteById(id);
    }

}
