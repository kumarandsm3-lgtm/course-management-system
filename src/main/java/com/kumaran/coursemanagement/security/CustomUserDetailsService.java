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

        String role = appUser.getRole().trim().toUpperCase();

        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_" + role)
        );

        System.out.println("Logged in email : " + appUser.getEmail());
        System.out.println("Logged in role  : " + role);
        System.out.println("Authority       : ROLE_" + role);

        return new org.springframework.security.core.userdetails.User(
                appUser.getEmail(),
                appUser.getPassword(),
                authorities
        );
    }
}