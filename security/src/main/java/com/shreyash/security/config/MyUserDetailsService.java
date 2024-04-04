package com.shreyash.security.config;

import com.shreyash.security.entity.MyUser;
import com.shreyash.security.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private MyUserRepository myUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = myUserRepository.findByUsername(username);
        var userObj = user.get();
        if(user.isPresent()){
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(userObj.getRole())
                    .build();
        }else {
            throw new UsernameNotFoundException(username);
        }
    }

    private String[] getRoles(MyUser user){
        if(user.getRole()== null){
            return new String[]{"USER"};
        }else {
         return user.getRole().split(",");
        }
    }
}
