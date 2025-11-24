package org.example.carrental.service;

import org.example.carrental.model.User;
import org.example.carrental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public UserDetailsService userDetailsService() {

        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) {
                User user = repository.findByEmail(email);
                user.authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleType()));
                return user;
            }
        };
    }

    public boolean hasId(int id){
        String username =  ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = repository.findByEmail(username);
        return user.getId() == id;
    }
}
