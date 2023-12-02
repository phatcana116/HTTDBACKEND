package com.example.HTTD.Service;

import com.example.HTTD.Entity.User;

import java.util.Optional;


public interface UserService {
    Optional<User> findByUsername(String username);
}
