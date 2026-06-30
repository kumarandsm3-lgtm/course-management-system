package com.kumaran.coursemanagement.security;

import com.kumaran.coursemanagement.entity.AppUser;
import com.kumaran.coursemanagement.repository.AppUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepository repository;

    public CustomUserDetailsService(AppUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        AppUser appUser = repository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found : " + email));

        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_" + appUser.getRole())
        );

        return new org.springframework.security.core.userdetails.User(
                appUser.getEmail(),
                appUser.getPassword(),
                authorities
        );
    }
}