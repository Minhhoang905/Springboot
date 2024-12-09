package com.example.AptechSpring.service.iml;

import com.example.AptechSpring.entity.LoginEntity;
import com.example.AptechSpring.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private LoginRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        LoginEntity user = userRepository.findByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + name);
        }
        return org.springframework.security.core.userdetails.User
            .withUsername(user.getName())
            .password(user.getPass())
            .roles(user.getRole())
            .build();
    }
}
