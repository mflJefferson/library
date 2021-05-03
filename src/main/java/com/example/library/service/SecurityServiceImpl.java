package com.example.library.service;

import com.example.library.entity.Authorization;
import com.example.library.entity.User;
import com.example.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("securityService")
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    UserRepository userRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return org.springframework.security.core.userdetails.User.builder().username(username).password(user.getPassword())
                .authorities(user.getAuthorizationSet().stream()
                        .map(Authorization::getName).collect(Collectors.toList())
                        .toArray(new String[user.getAuthorizationSet().size()]))
                .build();
    }
}
