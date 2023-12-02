package com.example.HTTD.Service.IMPL;

import com.example.HTTD.Entity.User;
import com.example.HTTD.Repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username: "+ username));

        Set<GrantedAuthority> authorities = user
                .getRoles()
                .stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
        String username1 = user.getUsername();
        String email = user.getPassword();

        // Create a custom UserDetails object with additional information
        CustomUserDetails customUserDetails = new CustomUserDetails(
                user.getEmail(),
                user.getPassword(),
                authorities,
                username1,
                email
        );

        return customUserDetails;
    }
    public class CustomUserDetails extends org.springframework.security.core.userdetails.User {
        private final String firstName;
        private final String lastName;

        public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
                                 String firstName, String lastName) {
            super(username, password, authorities);
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
    }

}
