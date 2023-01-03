package com.example.subscription_module.subscriberapi_main;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserDetailsDbRepository userrepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        //Logic to get the user form the Database
        UserDetailsFromDb subject=userrepo.findByUsername(userName);
        List<SimpleGrantedAuthority> roles=Arrays.asList(new SimpleGrantedAuthority(subject.getRole()));

        return new User(subject.getUsername(),subject.getPassword(),roles);
    }
}
