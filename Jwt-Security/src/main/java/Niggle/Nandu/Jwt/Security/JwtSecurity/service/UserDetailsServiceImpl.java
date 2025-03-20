package Niggle.Nandu.Jwt.Security.JwtSecurity.service;

import Niggle.Nandu.Jwt.Security.JwtSecurity.model.UserEntity;
import Niggle.Nandu.Jwt.Security.JwtSecurity.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("user not found: "+ username));

        return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
        // If using roles, replace emptyList() with user.getRoles()
    }
}
