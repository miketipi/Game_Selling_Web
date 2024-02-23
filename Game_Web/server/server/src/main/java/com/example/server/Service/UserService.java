package com.example.server.Service;

import com.example.server.Models.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {
    List<User> getAllUser();
    Optional<User> getUserById(Long id);
}
