package com.example.library.service;

import com.example.library.entity.User;

public interface UserService {
    User createUser(String name, String email, String password);
}
