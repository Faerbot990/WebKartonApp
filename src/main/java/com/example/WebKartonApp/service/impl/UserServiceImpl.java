package com.example.WebKartonApp.service.impl;


import com.example.WebKartonApp.model.Role;
import com.example.WebKartonApp.model.User;
import com.example.WebKartonApp.repo.UserRepository;
import com.example.WebKartonApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("userDetailsServiceImpl")
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;

//    private static final Map<String, User> userMap = new HashMap<>();
//
//            static {
//                Set<Role> roles = new HashSet<>();
//                roles.add(Role.ROLE_ADMIN);
//               userMap.put("admin", new User(1L, "admin", "dsd",roles, Collections.emptyList()));
//           }


    @Value("${hostname}")
    private String hostname;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getOne(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, LockedException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}
