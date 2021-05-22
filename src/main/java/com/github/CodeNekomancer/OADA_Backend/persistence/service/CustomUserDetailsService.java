package com.github.CodeNekomancer.OADA_Backend.persistence.service;

import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.ADAcc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private ADAccService userEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ADAcc> user = userEntityService.findByUserName(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException(username + " not found");
        }
    }

    public UserDetails loadUserById(String id) throws UsernameNotFoundException {
        Optional<ADAcc> user = userEntityService.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException(id + " not found");
        }
    }
}
